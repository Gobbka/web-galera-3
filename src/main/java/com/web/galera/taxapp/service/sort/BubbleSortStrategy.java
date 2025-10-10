package com.web.galera.taxapp.service.sort;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class BubbleSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        List<T> sortedList = new java.util.ArrayList<>(list);
        int n = sortedList.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(sortedList.get(j), sortedList.get(j + 1)) > 0) {
                    T temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        
        return sortedList;
    }
}