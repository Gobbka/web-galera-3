package com.web.galera.taxapp.repository;

import com.web.galera.taxapp.ui.Prompter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class CliRepository<TEntity> implements Repository<TEntity> {

    private final Function<Prompter, TEntity> builder;
    private final Prompter prompter;

    @Override
    public List<TEntity> getList(int size) {
        return IntStream.range(0,size)
                .boxed()
                .map(ignored -> this.builder.apply(prompter))
                .toList();
    }
}
