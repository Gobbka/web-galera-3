package com.web.galera.taxapp.service.sort;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
public class SortService<T> {

    private final SortStrategy<T> sortStrategy;
    private final Comparator<T> comparator;
    private final ExecutorService executorService;

    public SortService(SortStrategy<T> sortStrategy, Comparator<T> comparator) {
        this.sortStrategy = sortStrategy;
        this.comparator = comparator;
        this.executorService = Executors.newFixedThreadPool(2); // Минимум 2 потока
    }

    public CompletableFuture<List<T>> sortAsync(List<T> list) {
        return CompletableFuture.supplyAsync(() -> 
            sortStrategy.sort(list, comparator), 
            executorService
        );
    }

    public List<T> sort(List<T> list) {
        try {
            return sortAsync(list).join();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сортировке", e);
        }
    }

    public void shutdown() {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}