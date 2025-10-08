package com.web.galera.taxapp.datasource.random;

import com.web.galera.taxapp.entity.TaxAccount;

import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class RandomTaxAccountDataSource implements Supplier<TaxAccount> {

    private final Random random = new Random();
    private final String[] currencies = {"RUB", "USD", "EUR"};

    @Override
    public TaxAccount get() {
        return TaxAccount.builder()
                .accountId(randomLong(100000, 999999))
                .taxpayerId(UUID.randomUUID().toString().substring(0, 8))
                .balance(randomDouble(0, 10_000_000))
                .currency(randomCurrency())
                .isActive(random.nextBoolean())
                .taxYear(randomInt(2000, 2025))
                .build();
    }

    private long randomLong(long min, long max) {
        return min + (long) (random.nextDouble() * (max - min + 1));
    }

    private double randomDouble(double min, double max) {
        return Math.round((min + random.nextDouble() * (max - min)) * 100.0) / 100.0; // два знака после запятой
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private String randomCurrency() {
        return currencies[random.nextInt(currencies.length)];
    }
}
