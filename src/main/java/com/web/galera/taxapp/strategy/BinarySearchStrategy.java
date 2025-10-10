package com.web.galera.taxapp.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BinarySearchStrategy<T> implements SearchStrategy<T> {
    
    private final Comparator<T> comparator;
    
    public BinarySearchStrategy(Comparator<T> comparator) {
        this.comparator = Objects.requireNonNull(comparator, "Comparator cannot be null");
    }
    
    @Override
    public int search(List<T> list, T target) {
        Objects.requireNonNull(list, "List cannot be null");
        Objects.requireNonNull(target, "Target element cannot be null");
        
        if (list.isEmpty()) {
            return -1;
        }
        
        int left = 0;
        int right = list.size() - 1;
        
        while (left <= right) {
            int mid = (left + right) >>> 1;
            T midElement = list.get(mid);
            
            int comparison = comparator.compare(target, midElement);
            
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return -1;
    }
    
    public Comparator<T> getComparator() {
        return comparator;
    }
}
