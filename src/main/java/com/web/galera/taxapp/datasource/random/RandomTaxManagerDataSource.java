package com.web.galera.taxapp.datasource.random;

import com.web.galera.taxapp.entity.TaxManager;

import java.util.Random;
import java.util.function.Supplier;

public class RandomTaxManagerDataSource implements Supplier<TaxManager> {
    private final Random random = new Random();
    private final String[] departments = {"Налоговый контроль", "Аудит", "Аналитика", "Бухгалтерия"};
    private final String[] firstNames = {"Иван", "Анна", "Пётр", "Мария", "Алексей", "Екатерина"};
    private final String[] lastNames = {"Иванов", "Петров", "Сидоров", "Кузнецова", "Смирнов", "Попова"};

    public TaxManager get() {
        return TaxManager.builder()
                .managerId(randomInt(1000, 9999))
                .firstName(randomFirstName())
                .lastName(randomLastName())
                .department(randomDepartment())
                .yearsOfExperience(randomInt(1, 40))
                .salary(randomDouble(30_000, 300_000))
                .isSenior(random.nextBoolean())
                .build();
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private double randomDouble(double min, double max) {
        return Math.round((min + random.nextDouble() * (max - min)) * 100.0) / 100.0;
    }

    private String randomFirstName() {
        return firstNames[random.nextInt(firstNames.length)];
    }

    private String randomLastName() {
        return lastNames[random.nextInt(lastNames.length)];
    }

    private String randomDepartment() {
        return departments[random.nextInt(departments.length)];
    }
}
