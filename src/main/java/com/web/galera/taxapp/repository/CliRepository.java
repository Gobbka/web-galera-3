package com.web.galera.taxapp.repository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class CliRepository<TEntity> implements Repository<TEntity> {

    private final Function<Scanner, TEntity> builder;
    private final Scanner scanner;

    @Override
    public List<TEntity> getList(int size) {
        return IntStream.range(0,size)
                .boxed()
                .map(ignored -> this.builder.apply(scanner))
                .toList();
    }
}
