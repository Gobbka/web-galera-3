package com.web.galera.taxapp.ui;

import java.util.Collection;
import java.util.Map;

public interface UserInterface {
    void title(String text);

    void print(String message);

    void error(String message);

    String ask(String question);

    int askInt(String question);

    <K, V> K choose(String title, Map<K, V> items);

    <T> void printList(Collection<T> items);

    boolean confirm(String question);

    Prompter getPrompter();
}
