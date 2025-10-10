package com.web.galera.taxapp.ui;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class CliInterface implements UserInterface {

    private final CliPrompter prompt;

    public CliInterface(Scanner scanner) {
        this.prompt = new CliPrompter(scanner);
    }

    /** Вывести заголовок */
    @Override
    public void title(String text) {
        System.out.println("=--- " + text + " ---=");
    }

    /** Просто вывести сообщение пользователю */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /** Вывести ошибку */
    @Override
    public void error(String message) {
        System.err.println("Ошибка: " + message);
    }

    /** Запросить строку у пользователя */
    @Override
    public String ask(String question) {
        return prompt.askString(question);
    }

    /** Запросить число */
    @Override
    public int askInt(String question) {
        return prompt.askInt(question);
    }

    @Override
    public <K, V> K choose(String title, Map<K, V> items) {
        title(title);

        var keys = items.keySet().stream().toList(); // фиксируем порядок
        for (int i = 0; i < keys.size(); i++) {
            K key = keys.get(i);
            System.out.println((i + 1) + ". " + items.get(key)); // просто toString()
        }
        System.out.println("0. Выход");

        int index = prompt.askInt("Выберите пункт");
        if (index == 0) return null;
        if (index < 1 || index > keys.size()) {
            error("Некорректный выбор");
            return null;
        }

        return keys.get(index - 1); // возвращаем ключ
    }

    /** Вывести список объектов */
    @Override
    public <T> void printList(Collection<T> items) {
        items.forEach(System.out::println);
    }

    /** Подтверждение действия (Y/N) */
    @Override
    public boolean confirm(String question) {
        String answer = prompt.askString(question + " (y/n)");
        return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes");
    }

    @Override
    public Prompter getPrompter() {
        return prompt;
    }
}
