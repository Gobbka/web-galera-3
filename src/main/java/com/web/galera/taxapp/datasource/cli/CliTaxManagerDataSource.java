package com.web.galera.taxapp.datasource.cli;

import com.web.galera.taxapp.entity.TaxManager;
import com.web.galera.taxapp.util.CliPrompter;

import java.util.Scanner;

public class CliTaxManagerDataSource {

    public static TaxManager read(Scanner scanner) {
        var prompter = new CliPrompter(scanner);

        return TaxManager.builder()
                .managerId(prompter.askInt("Введите ID менеджера (целое число):"))
                .firstName(prompter.askString("Введите имя менеджера:"))
                .lastName(prompter.askString("Введите фамилию менеджера:"))
                .department(prompter.askString("Введите название отдела (например, Налоговый контроль):"))
                .yearsOfExperience(prompter.askInt("Введите стаж работы (в годах):"))
                .salary(prompter.askDouble("Введите заработную плату (в рублях):"))
                .isSenior(prompter.askBoolean("Является ли менеджер старшим? (true/false):"))
                .build();
    }
}