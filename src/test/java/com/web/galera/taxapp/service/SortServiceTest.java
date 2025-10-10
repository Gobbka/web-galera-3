package com.web.galera.taxapp.service;

import com.web.galera.taxapp.entity.TaxAccount;
import com.web.galera.taxapp.comparator.TaxAccountComparators;
import com.web.galera.taxapp.service.sort.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class SortServiceTest {

    @Test
    public void testBubbleSortStrategy() throws ExecutionException, InterruptedException {
        List<TaxAccount> accounts = List.of(
                TaxAccount.builder().accountId(1003L).balance(30000.0).build(),
                TaxAccount.builder().accountId(1001L).balance(15000.0).build(),
                TaxAccount.builder().accountId(1002L).balance(25000.0).build()
        );

        SortService<TaxAccount> sortService = SortServiceFactory.createBubbleSortService(
                TaxAccountComparators.byAccountId()
        );

        var future = sortService.sortAsync(accounts);
        List<TaxAccount> sortedAccounts = future.get();

        assertEquals(1001L, sortedAccounts.get(0).getAccountId());
        assertEquals(1002L, sortedAccounts.get(1).getAccountId());
        assertEquals(1003L, sortedAccounts.get(2).getAccountId());

        sortService.shutdown();
    }

    @Test
    public void testQuickSortStrategy() throws ExecutionException, InterruptedException {
        List<TaxAccount> accounts = List.of(
                TaxAccount.builder().accountId(1005L).balance(50000.0).build(),
                TaxAccount.builder().accountId(1001L).balance(10000.0).build(),
                TaxAccount.builder().accountId(1003L).balance(30000.0).build()
        );

        SortService<TaxAccount> sortService = SortServiceFactory.createQuickSortService(
                TaxAccountComparators.byBalance()
        );

        List<TaxAccount> sortedAccounts = sortService.sort(accounts);

        // Проверка результатов
        assertEquals(10000.0, sortedAccounts.get(0).getBalance());
        assertEquals(30000.0, sortedAccounts.get(1).getBalance());
        assertEquals(50000.0, sortedAccounts.get(2).getBalance());

        sortService.shutdown();
    }

    @Test
    public void testMergeSortStrategy() throws ExecutionException, InterruptedException {
        List<TaxAccount> accounts = List.of(
                TaxAccount.builder().accountId(1004L).balance(40000.0).build(),
                TaxAccount.builder().accountId(1002L).balance(20000.0).build(),
                TaxAccount.builder().accountId(1001L).balance(10000.0).build()
        );

        SortService<TaxAccount> sortService = SortServiceFactory.createMergeSortService(
                TaxAccountComparators.byAccountId()
        );

        List<TaxAccount> sortedAccounts = sortService.sort(accounts);

        // Проверка результатов
        assertEquals(1001L, sortedAccounts.get(0).getAccountId());
        assertEquals(1002L, sortedAccounts.get(1).getAccountId());
        assertEquals(1004L, sortedAccounts.get(2).getAccountId());

        sortService.shutdown();
    }

    @Test
    public void testSortWithEmptyList() {
        List<TaxAccount> emptyList = List.of();

        SortService<TaxAccount> sortService = SortServiceFactory.createBubbleSortService(
                TaxAccountComparators.byAccountId()
        );

        List<TaxAccount> result = sortService.sort(emptyList);

        assertTrue(result.isEmpty());
        sortService.shutdown();
    }

    @Test
    public void testSortWithSingleElement() {
        List<TaxAccount> singleElementList = List.of(
                TaxAccount.builder().accountId(1001L).balance(15000.0).build()
        );

        SortService<TaxAccount> sortService = SortServiceFactory.createQuickSortService(
                TaxAccountComparators.byAccountId()
        );

        List<TaxAccount> result = sortService.sort(singleElementList);

        assertEquals(1, result.size());
        assertEquals(1001L, result.get(0).getAccountId());
        sortService.shutdown();
    }

    @Test
    public void testDifferentComparators() {
        List<TaxAccount> accounts = List.of(
                TaxAccount.builder().accountId(1003L).balance(30000.0).taxYear(2023).build(),
                TaxAccount.builder().accountId(1001L).balance(15000.0).taxYear(2024).build(),
                TaxAccount.builder().accountId(1002L).balance(25000.0).taxYear(2022).build()
        );


        SortService<TaxAccount> sortServiceByYear = SortServiceFactory.createMergeSortService(
                TaxAccountComparators.byTaxYear()
        );

        List<TaxAccount> sortedByYear = sortServiceByYear.sort(accounts);

        assertEquals(2022, sortedByYear.get(0).getTaxYear());
        assertEquals(2023, sortedByYear.get(1).getTaxYear());
        assertEquals(2024, sortedByYear.get(2).getTaxYear());

        sortServiceByYear.shutdown();
    }
}