package com.web.galera.taxapp.comparator;

import com.web.galera.taxapp.entity.TaxAccount;
import java.util.Comparator;

public final class TaxAccountComparators {

    private TaxAccountComparators() {
        throw new AssertionError("Класс не может быть создан");
    }

    public static Comparator<TaxAccount> byAccountId() {
        return Comparator.comparing(TaxAccount::getAccountId,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxAccount> byTaxpayerId() {
        return Comparator.comparing(TaxAccount::getTaxpayerId,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    public static Comparator<TaxAccount> byBalance() {
        return Comparator.comparing(TaxAccount::getBalance,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxAccount> byCurrency() {
        return Comparator.comparing(TaxAccount::getCurrency,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    public static Comparator<TaxAccount> byIsActive() {
        return Comparator.comparing(TaxAccount::getIsActive,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxAccount> byTaxYear() {
        return Comparator.comparing(TaxAccount::getTaxYear,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }
}