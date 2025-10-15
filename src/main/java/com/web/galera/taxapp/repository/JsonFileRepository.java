package com.web.galera.taxapp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class JsonFileRepository<TEntity> implements Repository<TEntity> {

    private final File file;
    private final Function<String, List<TEntity>> mapper;

    @Override
    public List<TEntity> getList(int size) {
        try {
            String content = Files.readString(file.toPath());
            return this.mapper.apply(content).stream().limit(size).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
