package com.web.galera.taxapp.factory;

import com.web.galera.taxapp.repository.Repository;
import com.web.galera.taxapp.ui.Prompter;

import java.util.Comparator;

public interface EntityFactory<TEntity> {
    Repository<TEntity> getRandomRepository();
    Repository<TEntity> getCliRepository(Prompter prompter);
    Repository<TEntity> getJsonFileRepository();
    Comparator<TEntity> getComparator();
}
