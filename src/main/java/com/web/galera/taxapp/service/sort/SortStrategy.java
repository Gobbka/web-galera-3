package com.web.galera.taxapp.service.sort;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy<T> {
    List<T> sort(List<T> list, Comparator<T> comparator);
}