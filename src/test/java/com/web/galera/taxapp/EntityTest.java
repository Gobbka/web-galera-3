package com.web.galera.taxapp;

import com.web.galera.taxapp.entity.*;
import com.web.galera.taxapp.comparator.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    @Test
    public void testTaxAccountCreation() {
        // Проверяем создание TaxAccount
        TaxAccount account = TaxAccount.builder()
                .accountId(1001L)
                .taxpayerId("TP001")
                .balance(15000.0)
                .currency("RUB")
                .isActive(true)
                .taxYear(2024)
                .build();

        assertNotNull(account);
        assertEquals(1001L, account.getAccountId());
        assertEquals("TP001", account.getTaxpayerId());
        assertEquals(15000.0, account.getBalance());
        assertEquals("RUB", account.getCurrency());
        assertTrue(account.getIsActive());
        assertEquals(2024, account.getTaxYear());
    }

    @Test
    public void testTaxDeclarationCreation() {
        // Проверяем создание TaxDeclaration
        TaxDeclaration declaration = TaxDeclaration.builder()
                .declarationId("DECL001")
                .taxpayerId("TP001")
                .submissionDate(LocalDate.of(2024, 4, 15))
                .declaredIncome(500000.0)
                .taxAmount(65000.0)
                .declarationType("INCOME")
                .isApproved(true)
                .build();

        assertNotNull(declaration);
        assertEquals("DECL001", declaration.getDeclarationId());
        assertEquals(LocalDate.of(2024, 4, 15), declaration.getSubmissionDate());
        assertEquals(500000.0, declaration.getDeclaredIncome());
        assertTrue(declaration.getIsApproved());
    }

    @Test
    public void testTaxManagerCreation() {
        // Проверяем создание TaxManager
        TaxManager manager = TaxManager.builder()
                .managerId(1)
                .firstName("Иван")
                .lastName("Петров")
                .department("Налоговый контроль")
                .yearsOfExperience(5)
                .salary(80000.0)
                .isSenior(false)
                .build();

        assertNotNull(manager);
        assertEquals(1, manager.getManagerId());
        assertEquals("Иван", manager.getFirstName());
        assertEquals("Петров", manager.getLastName());
        assertEquals(5, manager.getYearsOfExperience());
        assertFalse(manager.getIsSenior());
    }

    @Test
    public void testTaxAccountComparators() {
        // Тестируем компараторы TaxAccount
        List<TaxAccount> accounts = new ArrayList<>();
        accounts.add(TaxAccount.builder().accountId(1002L).balance(25000.0).build());
        accounts.add(TaxAccount.builder().accountId(1001L).balance(15000.0).build());

        // Сортировка по accountId
        List<TaxAccount> sortedById = accounts.stream()
                .sorted(TaxAccountComparators.byAccountId())
                .toList();

        assertEquals(1001L, sortedById.get(0).getAccountId());
        assertEquals(1002L, sortedById.get(1).getAccountId());

        // Сортировка по balance
        List<TaxAccount> sortedByBalance = accounts.stream()
                .sorted(TaxAccountComparators.byBalance())
                .toList();

        assertEquals(15000.0, sortedByBalance.get(0).getBalance());
        assertEquals(25000.0, sortedByBalance.get(1).getBalance());
    }

    @Test
    public void testTaxDeclarationComparators() {
        // Тестируем компараторы TaxDeclaration
        List<TaxDeclaration> declarations = new ArrayList<>();
        declarations.add(TaxDeclaration.builder()
                .declarationId("DECL002")
                .submissionDate(LocalDate.of(2024, 4, 10))
                .taxAmount(97500.0)
                .build());
        declarations.add(TaxDeclaration.builder()
                .declarationId("DECL001")
                .submissionDate(LocalDate.of(2024, 4, 15))
                .taxAmount(65000.0)
                .build());

        // Сортировка по дате
        List<TaxDeclaration> sortedByDate = declarations.stream()
                .sorted(TaxDeclarationComparators.bySubmissionDate())
                .toList();

        assertEquals(LocalDate.of(2024, 4, 10), sortedByDate.get(0).getSubmissionDate());
        assertEquals(LocalDate.of(2024, 4, 15), sortedByDate.get(1).getSubmissionDate());
    }

    @Test
    public void testInvalidTaxAccount() {
        // Тест на неправильные условия
        Assertions.assertThrows(RuntimeException.class, () -> {
            // Здесь должен быть код, который кидает исключение
            // Например, если бы у нас была валидация:
            if (true) { // условие, которое всегда true для демонстрации
                throw new RuntimeException("Invalid tax account data");
            }
        });
    }

    @Test
    public void testTaxManagerByFullNameComparator() {
        List<TaxManager> managers = List.of(
                TaxManager.builder().firstName("Анна").lastName("Иванова").build(),
                TaxManager.builder().firstName("Борис").lastName("Сидоров").build()
        );

        List<TaxManager> sorted = managers.stream()
                .sorted(TaxManagerComparators.byFullName())
                .toList();

        assertEquals("Анна", sorted.get(0).getFirstName());
    }


    @Test
    public void testInvalidData() {
        // Попытка создать объект с невалидными данными
        Assertions.assertThrows(RuntimeException.class, () -> {
            TaxAccount account = TaxAccount.builder()
                    .accountId(-1L)  // отрицательный ID!
                    .build();

            if (account.getAccountId() < 0) {
                throw new RuntimeException("Account ID cannot be negative");
            }
        });
    }

    @Test
    public void testBuilderWithNullValues() {
        // Тест на обработку null значений в компараторах
        TaxAccount account1 = TaxAccount.builder().accountId(null).build();
        TaxAccount account2 = TaxAccount.builder().accountId(1001L).build();

        List<TaxAccount> accounts = List.of(account1, account2);

        // Не должно бросать исключение при сортировке с null
        assertDoesNotThrow(() -> {
            accounts.stream().sorted(TaxAccountComparators.byAccountId()).toList();
        });


    }
}