package com.web.galera.taxapp.controller;

import com.web.galera.taxapp.entity.Entity;
import com.web.galera.taxapp.factory.EntityFactory;
import com.web.galera.taxapp.factory.TaxAccountFactory;
import com.web.galera.taxapp.factory.TaxDeclarationFactory;
import com.web.galera.taxapp.factory.TaxManagerFactory;
import com.web.galera.taxapp.repository.Repository;
import com.web.galera.taxapp.service.SearchService;
import com.web.galera.taxapp.service.sort.SortService;
import com.web.galera.taxapp.service.sort.SortStrategy;
import com.web.galera.taxapp.strategy.*;
import com.web.galera.taxapp.ui.CliInterface;
import com.web.galera.taxapp.ui.UserInterface;

import java.util.Map;

public class AppController {

    private final UserInterface ui = new CliInterface(new java.util.Scanner(System.in));

    public void run() {
        var sortService = new SortService<Entity>(null, null);

        try {
            while (true) {
                var factory = getFactory();
                if (factory == null) break;

                var source = getSource();
                if (source == null) break;

                var repository = getRepository(factory, source);

                int size = ui.askInt("Введите количество элементов");
                var list = repository.getList(size);

                var sortChoice = getSortMethod();
                if (sortChoice == null) break;

                sortService.setComparator(factory.getComparator());
                sortService.setSortStrategy(sortChoice);
                var sorted = sortService.sort(list);

                ui.printList(sorted);

                if (ui.confirm("Вы хотите выполнить поиск?")) {
                    var target = factory.getCliRepository(ui.getPrompter())
                            .getList(1)
                            .getFirst();

                    if (target == null) {
                        ui.error("Поисковый элемент не введён");
                        continue;
                    }

                    var searchService = new SearchService<>(new BinarySearchStrategy<>(factory.getComparator()));
                    int index = searchService.search(sorted, target);
                    if (index < 0) {
                        ui.error("Элемент не найден");
                    } else {
                        ui.print("Найденный элемент:");
                        ui.printList(sorted);
                    }
                }

                if (!ui.confirm("Хотите выполнить ещё одну операцию?")) break;
            }
        } finally {
            sortService.shutdown();
        }
    }

    @SuppressWarnings("unchecked")
    private EntityFactory<Entity> getFactory() {
        return (EntityFactory<Entity>) ui.choose("Список моделей", Map.of(
                new TaxAccountFactory(), "Налоговый счет",
                new TaxDeclarationFactory(), "Налоговая декларация",
                new TaxManagerFactory(), "Налоговый менеджер"
        ));
    }

    private String getSource() {
        return ui.choose(
                "Способы получения данных",
                Map.of(
                        "file", "Из файла",
                        "random", "Случайно",
                        "cli", "Ввести вручную"
                )
        );
    }

    private SortStrategy<Entity> getSortMethod() {
        return ui.choose(
                "Выберите способ сортировки",
                Map.of(
                        new BubbleSortStrategy<Entity>(), "Пузырьковая сортировка",
                        new QuickSortStrategy<Entity>(), "Быстрая сортировка",
                        new MergeSortStrategy<Entity>(), "Merge сортировка"
                )
        );
    }

    private <T extends Entity> Repository<T> getRepository(EntityFactory<T> factory, String source) {
        return switch (source) {
            case "file" -> factory.getJsonFileRepository();
            case "random" -> factory.getRandomRepository();
            case "cli" -> factory.getCliRepository(this.ui.getPrompter());
            default -> throw new IllegalStateException("Неверный источник данных: " + source);
        };
    }
}