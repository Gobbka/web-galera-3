package com.web.galera.taxapp.datasource.cli;

import com.web.galera.taxapp.entity.TaxDeclaration;
import com.web.galera.taxapp.ui.Prompter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CliTaxDeclarationDataSource {

    public static TaxDeclaration read(Prompter prompter) {
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate submissionDate = null;
        while (submissionDate == null) {
            String input = prompter.askString("Введите дату подачи декларации (в формате yyyy-MM-dd):");
            try {
                submissionDate = LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат даты. Пример: 2025-03-15");
            }
        }

        return TaxDeclaration.builder()
                .declarationId(prompter.askString("Введите ID декларации:"))
                .taxpayerId(prompter.askString("Введите ID плательщика:"))
                .submissionDate(submissionDate)
                .declaredIncome(prompter.askDouble("Введите задекларированный доход (в рублях):"))
                .taxAmount(prompter.askDouble("Введите сумму налога (в рублях):"))
                .declarationType(prompter.askString("Введите тип декларации (например, НДФЛ, НДС):"))
                .isApproved(prompter.askBoolean("Декларация одобрена? (true/false):"))
                .build();
    }
}