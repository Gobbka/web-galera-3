package com.web.galera.taxapp.service;

import com.web.galera.taxapp.strategy.BinarySearchStrategy;
import com.web.galera.taxapp.strategy.SearchStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchServiceTest {

    @Test
    public void testSearchFound() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        
        assertEquals(2, service.search(list, 5));
        assertEquals(0, service.search(list, 1));
        assertEquals(4, service.search(list, 9));
    }

    @Test
    public void testSearchNotFound() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        
        assertEquals(-1, service.search(list, 2));
        assertEquals(-1, service.search(list, 6));
        assertEquals(-1, service.search(list, 0));
        assertEquals(-1, service.search(list, 10));
    }

    @Test
    public void testSearchEmptyList() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        List<Integer> list = Collections.emptyList();
        
        assertEquals(-1, service.search(list, 5));
    }

    @Test
    public void testSearchSingleElement() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        List<Integer> list = Collections.singletonList(5);
        
        assertEquals(0, service.search(list, 5));
        assertEquals(-1, service.search(list, 3));
    }

    @Test
    public void testSearchWithStrings() {
        SearchStrategy<String> strategy = new BinarySearchStrategy<>(String::compareTo);
        SearchService<String> service = new SearchService<>(strategy);
        
        List<String> list = Arrays.asList("apple", "banana", "cherry", "date");
        
        assertEquals(0, service.search(list, "apple"));
        assertEquals(2, service.search(list, "cherry"));
        assertEquals(-1, service.search(list, "grape"));
    }

    @Test
    public void testSearchWithCustomComparator() {
        Comparator<Integer> reverseComparator = (a, b) -> b.compareTo(a);
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(reverseComparator);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        List<Integer> list = Arrays.asList(9, 7, 5, 3, 1);
        
        assertEquals(2, service.search(list, 5));
        assertEquals(0, service.search(list, 9));
    }

    @Test
    public void testSearchWithNullList() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        assertThrows(NullPointerException.class, () -> service.search(null, 5));
    }

    @Test
    public void testSearchWithNullTarget() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        List<Integer> list = Arrays.asList(1, 3, 5);
        
        assertThrows(NullPointerException.class, () -> service.search(list, null));
    }

    @Test
    public void testConstructorWithNullStrategy() {
        assertThrows(NullPointerException.class, () -> new SearchService<Integer>(null));
    }

    @Test
    public void testGetSearchStrategy() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        assertNotNull(service.getSearchStrategy());
        assertEquals(strategy, service.getSearchStrategy());
    }

    @Test
    public void testSearchLargeList() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        
        assertEquals(7, service.search(list, 8));
        assertEquals(0, service.search(list, 1));
        assertEquals(14, service.search(list, 15));
        assertEquals(-1, service.search(list, 16));
    }

    @Test
    public void testSearchDuplicateElements() {
        SearchStrategy<Integer> strategy = new BinarySearchStrategy<>(Integer::compareTo);
        SearchService<Integer> service = new SearchService<>(strategy);
        
        List<Integer> list = Arrays.asList(1, 3, 3, 3, 5, 7, 9);
        
        int index = service.search(list, 3);
        assertTrue(index >= 1 && index <= 3);
        assertEquals(3, list.get(index));
    }
}
