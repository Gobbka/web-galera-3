package com.web.galera.taxapp.datasource.random;

import com.web.galera.taxapp.entity.TaxDeclaration;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class RandomTaxDeclarationDataSource implements Supplier<TaxDeclaration> {

    private final Random random = new Random();
    private final String[] declarationTypes = {"НДФЛ", "НДС", "ЕСН", "Акцизы"};

    @Override
    public TaxDeclaration get() {
        return TaxDeclaration.builder()
                .declarationId(UUID.randomUUID().toString().substring(0, 8))
                .taxpayerId(UUID.randomUUID().toString().substring(0, 8))
                .submissionDate(randomDate(2000, 2025))
                .declaredIncome(randomDouble(0, 10_000_000))
                .taxAmount(randomDouble(0, 1_000_000))
                .declarationType(randomDeclarationType())
                .isApproved(random.nextBoolean())
                .build();
    }

    private LocalDate randomDate(int startYear, int endYear) {
        int year = randomInt(startYear, endYear);
        int month = randomInt(1, 12);
        int day = randomInt(1, 28);
        return LocalDate.of(year, month, day);
    }

    private double randomDouble(double min, double max) {
        return Math.round((min + random.nextDouble() * (max - min)) * 100.0) / 100.0;
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private String randomDeclarationType() {
        return declarationTypes[random.nextInt(declarationTypes.length)];
    }
}
