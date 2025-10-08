package com.web.galera.taxapp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@RequiredArgsConstructor
public class JsonFileRepository<TEntity> implements Repository<TEntity> {

    private final File file;

    @Override
    public List<TEntity> getList(int size) {
        var objectMapper = new ObjectMapper();
        try {
            String content = Files.readString(file.toPath());
            return objectMapper.readValue(content, new TypeReference<List<TEntity>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
