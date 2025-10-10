package com.web.galera.taxapp.service.sort;

import com.web.galera.taxapp.strategy.BubbleSortStrategy;
import com.web.galera.taxapp.strategy.MergeSortStrategy;
import com.web.galera.taxapp.strategy.QuickSortStrategy;

import java.util.Comparator;

public final class SortServiceFactory {

    private SortServiceFactory() {
        throw new AssertionError("Класс не может быть создан");
    }

    public static <T> SortService<T> createBubbleSortService(Comparator<T> comparator) {
        return new SortService<>(new BubbleSortStrategy<>(), comparator);
    }

    public static <T> SortService<T> createQuickSortService(Comparator<T> comparator) {
        return new SortService<>(new QuickSortStrategy<>(), comparator);
    }

    public static <T> SortService<T> createMergeSortService(Comparator<T> comparator) {
        return new SortService<>(new MergeSortStrategy<>(), comparator);
    }
}