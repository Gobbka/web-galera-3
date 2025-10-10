package com.web.galera.taxapp.datasource.cli;

import com.web.galera.taxapp.entity.TaxAccount;
import com.web.galera.taxapp.ui.Prompter;

public class CliTaxAccountDataSource {

    public static TaxAccount read(Prompter prompter) {
        return TaxAccount.builder()
                .accountId(prompter.askLong("Введите номер счета"))
                .taxpayerId(prompter.askString("Введите id плательщика"))
                .balance(prompter.askDouble("Введите баланс"))
                .currency(prompter.askString( "Введите валюту"))
                .isActive(prompter.askBoolean("Введите активеность счета (true/false)"))
                .taxYear(prompter.askInt( "Введите год счета (например, 2025)"))
                .build();
    }
}
