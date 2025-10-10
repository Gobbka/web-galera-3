package com.web.galera.taxapp;

import com.web.galera.taxapp.entity.Entity;
import com.web.galera.taxapp.factory.EntityFactory;
import com.web.galera.taxapp.factory.TaxAccountFactory;
import com.web.galera.taxapp.factory.TaxDeclarationFactory;
import com.web.galera.taxapp.factory.TaxManagerFactory;
import com.web.galera.taxapp.service.SearchService;
import com.web.galera.taxapp.service.sort.SortService;
import com.web.galera.taxapp.strategy.BinarySearchStrategy;
import com.web.galera.taxapp.strategy.BubbleSortStrategy;
import com.web.galera.taxapp.strategy.MergeSortStrategy;
import com.web.galera.taxapp.strategy.QuickSortStrategy;
import com.web.galera.taxapp.util.CliPrompter;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var entityMap = new HashMap<String, EntityFactory<? extends Entity>>();
        var prompt = new CliPrompter(scanner);

        entityMap.put("tax-account", new TaxAccountFactory());
        entityMap.put("tax-declaration", new TaxDeclarationFactory());
        entityMap.put("tax-manager", new TaxManagerFactory());

        while (true) {
            try {
                System.out.println("=--- Список моделей ---=");
                entityMap.keySet().forEach(System.out::println);
                System.out.println("exit - выход");
                var modelCode = prompt.askString("Код модели");
                if(modelCode.equals("exit")) {
                    break;
                }
                if(!entityMap.containsKey(modelCode)) {
                    System.out.println("Модель " + modelCode + " не найдена.");
                    continue;
                }

                @SuppressWarnings("unchecked")
                var model = (EntityFactory<Entity>) entityMap.get(modelCode);

                System.out.println("=--- Способы получения данных ---=");
                System.out.println("1 - Из файла");
                System.out.println("2 - Рандом");
                System.out.println("3 - Ввести вручную");
                var repositoryIndex = prompt.askString("Выберите способ получения");

                var repository = switch (repositoryIndex) {
                    case "1" -> model.getJsonFileRepository("tax-account.json");
                    case "2" -> model.getRandomRepository();
                    case "3" -> model.getCliRepository(scanner);
                    default -> throw new IllegalStateException("Выбран некорректный способ получения данных: " + repositoryIndex);
                };

                var size = prompt.askInt("Введите количество элементов");
                var list = repository.getList(size);

                System.out.println("=--- Способы сортировки ---=");
                System.out.println("1 - Пузырьковая сортировка");
                System.out.println("2 - Быстрая сортировка");
                System.out.println("3 - Merge сортировка");
                var sort = prompt.askString("Выберите способ сортировки");

                var sortService = getEntitySortService(sort, model);
                var sorted = sortService.sort(list);

                sorted.forEach(System.out::println);

                System.out.println("=--- Поиск элемента ---=");
                var cliRepository = model.getCliRepository(scanner);
                var searchList = cliRepository.getList(1);
                var searchEntity = searchList.getFirst();
                if(searchEntity == null) {
                    throw new IllegalStateException();
                }

                var searchService = new SearchService<>(new BinarySearchStrategy<>(
                        model.getComparator()
                ));

                var index = searchService.search(sorted, searchEntity);
                if(index < 0) {
                    throw new IllegalStateException("Модель не найдена");
                }
                System.out.println(sorted.get(index));
            } catch (Exception ignored) {

            }
        }
    }

    private static SortService<Entity> getEntitySortService(String sort, EntityFactory<Entity> model) {
        var sortStrategy = switch (sort) {
            case "1" -> new BubbleSortStrategy<Entity>();
            case "2" -> new QuickSortStrategy<Entity>();
            case "3" -> new MergeSortStrategy<Entity>();
            default -> throw new IllegalStateException("Выбран некорректный способ получения данных: " + sort);
        };

        return new SortService<>(sortStrategy, model.getComparator());
    }

}
