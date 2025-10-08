package com.web.galera.taxapp.datasource.random;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class RandomIntDataSource implements Supplier<Integer> {

    private final Random random;

    public RandomIntDataSource() {
        this.random = new Random();
    }

    @Override
    public Integer get() {
        return random.nextInt();
    }
}
