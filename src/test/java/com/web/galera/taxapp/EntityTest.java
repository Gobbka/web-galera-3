package com.web.galera.taxapp;

import com.web.galera.taxapp.entity.TaxAccount;
import com.web.galera.taxapp.entity.TaxDeclaration;
import com.web.galera.taxapp.entity.TaxManager;
import com.web.galera.taxapp.comparator.TaxAccountComparators;
import com.web.galera.taxapp.comparator.TaxDeclarationComparators;
import com.web.galera.taxapp.comparator.TaxManagerComparators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntityTest {

    public static void main(String[] args) {
        // Тестируем создание объектов через Builder
        List<TaxAccount> taxAccounts = createTestTaxAccounts();
        List<TaxDeclaration> taxDeclarations = createTestTaxDeclarations();
        List<TaxManager> taxManagers = createTestTaxManagers();

        // Выводим созданные объекты
        System.out.println("=== Tax Accounts ===");
        taxAccounts.forEach(System.out::println);

        System.out.println("\n=== Tax Declarations ===");
        taxDeclarations.forEach(System.out::println);

        System.out.println("\n=== Tax Managers ===");
        taxManagers.forEach(System.out::println);

        // Тестируем компараторы
        testComparators(taxAccounts, taxDeclarations, taxManagers);
    }

    private static List<TaxAccount> createTestTaxAccounts() {
        List<TaxAccount> accounts = new ArrayList<>();

        accounts.add(new TaxAccount.Builder()
                .accountId(1001L)
                .taxpayerId("TP001")
                .balance(15000.0)
                .currency("RUB")
                .isActive(true)
                .taxYear(2024)
                .build());

        accounts.add(new TaxAccount.Builder()
                .accountId(1002L)
                .taxpayerId("TP002")
                .balance(25000.0)
                .currency("USD")
                .isActive(false)
                .taxYear(2023)
                .build());

        return accounts;
    }

    private static List<TaxDeclaration> createTestTaxDeclarations() {
        List<TaxDeclaration> declarations = new ArrayList<>();

        declarations.add(new TaxDeclaration.Builder()
                .declarationId("DECL001")
                .taxpayerId("TP001")
                .submissionDate(LocalDate.of(2024, 4, 15))
                .declaredIncome(500000.0)
                .taxAmount(65000.0)
                .declarationType("INCOME")
                .isApproved(true)
                .build());

        declarations.add(new TaxDeclaration.Builder()
                .declarationId("DECL002")
                .taxpayerId("TP002")
                .submissionDate(LocalDate.of(2024, 4, 10))
                .declaredIncome(750000.0)
                .taxAmount(97500.0)
                .declarationType("INCOME")
                .isApproved(false)
                .build());

        return declarations;
    }

    private static List<TaxManager> createTestTaxManagers() {
        List<TaxManager> managers = new ArrayList<>();

        managers.add(new TaxManager.Builder()
                .managerId(1)
                .firstName("Иван")
                .lastName("Петров")
                .department("Налоговый контроль")
                .yearsOfExperience(5)
                .salary(80000.0)
                .isSenior(false)
                .build());

        managers.add(new TaxManager.Builder()
                .managerId(2)
                .firstName("Мария")
                .lastName("Сидорова")
                .department("Аудит")
                .yearsOfExperience(12)
                .salary(120000.0)
                .isSenior(true)
                .build());

        return managers;
    }

    private static void testComparators(List<TaxAccount> accounts,
                                        List<TaxDeclaration> declarations,
                                        List<TaxManager> managers) {
        System.out.println("\n=== Testing Comparators ===");

        // Тестируем сортировку TaxAccount по балансу
        System.out.println("TaxAccounts sorted by balance:");
        accounts.stream()
                .sorted(TaxAccountComparators.byBalance())
                .forEach(System.out::println);

        // Тестируем сортировку TaxDeclaration по дате подачи
        System.out.println("\nTaxDeclarations sorted by submission date:");
        declarations.stream()
                .sorted(TaxDeclarationComparators.bySubmissionDate())
                .forEach(System.out::println);

        // Тестируем сортировку TaxManager по опыту работы
        System.out.println("\nTaxManagers sorted by years of experience:");
        managers.stream()
                .sorted(TaxManagerComparators.byYearsOfExperience())
                .forEach(System.out::println);
    }
}