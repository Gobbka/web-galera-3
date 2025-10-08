package com.web.galera.taxapp.repository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class RandomRepository<TEntity> implements Repository<TEntity> {

    private final Supplier<TEntity> supplier;

    @Override
    public List<TEntity> getList(int size) {
        return IntStream.range(0,size)
                .boxed()
                .map(ignored -> supplier.get())
                .toList();
    }
}
