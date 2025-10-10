package com.web.galera.taxapp.service;

import com.web.galera.taxapp.strategy.SearchStrategy;

import java.util.List;
import java.util.Objects;

public class SearchService<T> {
    
    private final SearchStrategy<T> searchStrategy;
    
    public SearchService(SearchStrategy<T> searchStrategy) {
        this.searchStrategy = Objects.requireNonNull(searchStrategy, "Search strategy cannot be null");
    }
    
    public int search(List<T> list, T target) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(target, "Target element cannot be null");
        return searchStrategy.search(list, target);
    }
    
    public SearchStrategy<T> getSearchStrategy() {
        return searchStrategy;
    }
}
