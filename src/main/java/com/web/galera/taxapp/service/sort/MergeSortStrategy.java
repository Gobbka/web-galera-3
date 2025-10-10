package com.web.galera.taxapp.service.sort;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class MergeSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        List<T> sortedList = new java.util.ArrayList<>(list);
        mergeSort(sortedList, 0, sortedList.size() - 1, comparator);
        return sortedList;
    }

    private void mergeSort(List<T> list, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(list, left, mid, comparator);
            mergeSort(list, mid + 1, right, comparator);
            merge(list, left, mid, right, comparator);
        }
    }

    private void merge(List<T> list, int left, int mid, int right, Comparator<T> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<T> leftList = new java.util.ArrayList<>(list.subList(left, left + n1));
        List<T> rightList = new java.util.ArrayList<>(list.subList(mid + 1, mid + 1 + n2));

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}