package com.web.galera.taxapp;

import com.web.galera.taxapp.factory.EntityFactory;
import com.web.galera.taxapp.factory.TaxAccountFactory;
import com.web.galera.taxapp.factory.TaxDeclarationFactory;
import com.web.galera.taxapp.factory.TaxManagerFactory;
import com.web.galera.taxapp.util.CliPrompter;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var entityMap = new HashMap<String, EntityFactory<?>>();
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
                var model = entityMap.get(modelCode);

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
                System.out.println(list);
            } catch (Exception ignored) {

            }
        }



    }

}
