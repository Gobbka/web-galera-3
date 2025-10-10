package com.web.galera.taxapp.factory;

import com.web.galera.taxapp.repository.Repository;

import java.util.Comparator;
import java.util.Scanner;

public interface EntityFactory<TEntity> {
    Repository<TEntity> getRandomRepository();
    Repository<TEntity> getCliRepository(Scanner scanner);
    Repository<TEntity> getJsonFileRepository(String filename);
    Comparator<TEntity> getComparator();
}
