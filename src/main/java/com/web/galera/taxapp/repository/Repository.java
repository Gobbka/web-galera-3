package com.web.galera.taxapp.repository;

import java.util.List;

public interface Repository<TEntity> {

    List<TEntity> getList(int size);

}
