package com.web.galera.taxapp.validator;

import com.web.galera.taxapp.entity.TaxDeclaration;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaxDeclarationValidatorTest {

    private final TaxDeclarationValidator validator = new TaxDeclarationValidator();

    @Test
    void validDeclarationShouldPass() {
        TaxDeclaration declaration = TaxDeclaration.builder()
                .declarationId("abcd1234")
                .taxpayerId("xyz98765")
                .submissionDate(LocalDate.now().minusDays(10))
                .declaredIncome(1_000_000.00)
                .taxAmount(100_000.00)
                .declarationType("НДС")
                .isApproved(true)
                .build();

        var result = validator.validate(declaration);
        assertTrue(result.isValid(), result.message());
    }

    @Test
    void invalidDeclarationShouldFail() {
        TaxDeclaration declaration = TaxDeclaration.builder()
                .declarationId("123") // короткий ID
                .taxpayerId(null) // null
                .submissionDate(LocalDate.of(1990, 1, 1)) // слишком старая дата
                .declaredIncome(-10.0)
                .taxAmount(2_000_000.0)
                .declarationType("ФНС") // недопустимый тип
                .isApproved(null)
                .build();

        var result = validator.validate(declaration);
        assertFalse(result.isValid());
    }
}
