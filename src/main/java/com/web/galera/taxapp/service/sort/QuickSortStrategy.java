package com.web.galera.taxapp.service.sort;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class QuickSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        List<T> sortedList = new java.util.ArrayList<>(list);
        quickSort(sortedList, 0, sortedList.size() - 1, comparator);
        return sortedList;
    }

    private void quickSort(List<T> list, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            quickSort(list, low, pi - 1, comparator);
            quickSort(list, pi + 1, high, comparator);
        }
    }

    private int partition(List<T> list, int low, int high, Comparator<T> comparator) {
        T pivot = list.get(high);
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        
        T temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        
        return i + 1;
    }
}