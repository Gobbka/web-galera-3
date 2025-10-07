package com.web.galera.taxapp.comparator;

import com.web.galera.taxapp.entity.TaxDeclaration;
import java.util.Comparator;

public class TaxDeclarationComparators {

    public static Comparator<TaxDeclaration> byDeclarationId() {
        return Comparator.comparing(TaxDeclaration::getDeclarationId,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    public static Comparator<TaxDeclaration> byTaxpayerId() {
        return Comparator.comparing(TaxDeclaration::getTaxpayerId,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    public static Comparator<TaxDeclaration> bySubmissionDate() {
        return Comparator.comparing(TaxDeclaration::getSubmissionDate,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxDeclaration> byDeclaredIncome() {
        return Comparator.comparing(TaxDeclaration::getDeclaredIncome,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxDeclaration> byTaxAmount() {
        return Comparator.comparing(TaxDeclaration::getTaxAmount,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public static Comparator<TaxDeclaration> byDeclarationType() {
        return Comparator.comparing(TaxDeclaration::getDeclarationType,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
    }

    public static Comparator<TaxDeclaration> byIsApproved() {
        return Comparator.comparing(TaxDeclaration::getIsApproved,
                Comparator.nullsLast(Comparator.naturalOrder()));
    }
}