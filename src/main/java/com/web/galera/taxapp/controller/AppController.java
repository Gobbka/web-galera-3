package com.web.galera.taxapp.controller;

import com.web.galera.taxapp.entity.Entity;
import com.web.galera.taxapp.factory.EntityFactory;
import com.web.galera.taxapp.factory.TaxAccountFactory;
import com.web.galera.taxapp.factory.TaxDeclarationFactory;
import com.web.galera.taxapp.factory.TaxManagerFactory;
import com.web.galera.taxapp.service.SearchService;
import com.web.galera.taxapp.service.sort.SortService;
import com.web.galera.taxapp.strategy.*;
import com.web.galera.taxapp.ui.CliInterface;
import com.web.galera.taxapp.ui.UserInterface;

import java.util.List;
import java.util.Map;

public class AppController {

    private final UserInterface ui = new CliInterface(new java.util.Scanner(System.in));

    public void run() {
        while (true) {
            @SuppressWarnings("unchecked")
            var factory = (EntityFactory<Entity>)ui.choose("Список моделей", Map.of(
                    new TaxAccountFactory(), "Налоговый счет",
                    new TaxDeclarationFactory(), "Налоговая декларация",
                    new TaxManagerFactory(), "Налоговый менеджер"
            ));
            if(factory == null) break;

            String source = ui.choose(
                    "Способы получения данных",
                    Map.of(
                            "file", "Из файла",
                            "random", "Случайно",
                            "cli", "Ввести вручную"
                    )
            );
            if (source == null) break;

            var repository = selectRepository(factory, source);

            int size = ui.askInt("Введите количество элементов");
            var list = repository.getList(size);

            String sortChoice = ui.choose(
                    "Выберите способ сортировки",
                    Map.of(
                            "bubble", "Пузырьковая сортировка",
                            "fast", "Быстрая сортировка",
                            "merge", "Merge сортировка"
                    )
            );
            if (sortChoice == null) break;

            var sorted = getSortService(sortChoice, factory).sort(list);
            ui.printList(sorted);

            if(ui.confirm("Вы хотите выполнить поиск?")) {
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
                    ui.printList(List.of(sorted.get(index)));
                }
            }

            if (!ui.confirm("Хотите выполнить ещё одну операцию?")) break;
        }

        ui.print("Bye");
    }

    private <T extends Entity> com.web.galera.taxapp.repository.Repository<T> selectRepository(EntityFactory<T> factory, String source) {
        return switch (source) {
            case "file" -> factory.getJsonFileRepository();
            case "random" -> factory.getRandomRepository();
            case "cli" -> factory.getCliRepository(this.ui.getPrompter());
            default -> throw new IllegalStateException("Неверный источник данных: " + source);
        };
    }

    private SortService<Entity> getSortService(String sort, EntityFactory<Entity> factory) {
        var strategy = switch (sort) {
            case "bubble" -> new BubbleSortStrategy<Entity>();
            case "fast" -> new QuickSortStrategy<Entity>();
            case "merge" -> new MergeSortStrategy<Entity>();
            default -> throw new IllegalStateException("Неверный способ сортировки: " + sort);
        };
        return new SortService<>(strategy, factory.getComparator());
    }
}