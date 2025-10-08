package com.web.galera.taxapp.comparator;

import com.web.galera.taxapp.entity.TaxManager;
import java.util.Comparator;

public final class TaxManagerComparators {

    private TaxManagerComparators() {
        throw new AssertionError("Класс не может быть создан");
    }

    public static Comparator<TaxManager> byManagerId() {
        return Comparator.comparing(TaxManager::getManagerId,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxManager> byFirstName() {
        return Comparator.comparing(TaxManager::getFirstName,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    public static Comparator<TaxManager> byLastName() {
        return Comparator.comparing(TaxManager::getLastName,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    public static Comparator<TaxManager> byFullName() {
        return Comparator
                .comparing(TaxManager::getFirstName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                .thenComparing(TaxManager::getLastName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }


    public static Comparator<TaxManager> byDepartment() {
        return Comparator.comparing(TaxManager::getDepartment,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    public static Comparator<TaxManager> byYearsOfExperience() {
        return Comparator.comparing(TaxManager::getYearsOfExperience,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxManager> bySalary() {
        return Comparator.comparing(TaxManager::getSalary,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxManager> byIsSenior() {
        return Comparator.comparing(TaxManager::getIsSenior,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }
}