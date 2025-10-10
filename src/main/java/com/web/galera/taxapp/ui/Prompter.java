package com.web.galera.taxapp.ui;

public interface Prompter {
    String askString(String prompt);

    long askLong(String prompt);

    double askDouble(String prompt);

    boolean askBoolean(String prompt);

    int askInt(String prompt);
}
