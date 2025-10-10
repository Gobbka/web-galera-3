package com.web.galera.taxapp.strategy;

import java.util.List;

public interface SearchStrategy<T> {
    int search(List<T> list, T target);
}
