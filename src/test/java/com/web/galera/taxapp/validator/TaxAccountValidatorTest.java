package com.web.galera.taxapp.validator;

import com.web.galera.taxapp.entity.TaxAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxAccountValidatorTest {

    private final TaxAccountValidator validator = new TaxAccountValidator();

    @Test
    void validTaxAccountShouldPass() {
        TaxAccount account = TaxAccount.builder()
                .accountId(123456L)
                .taxpayerId("abc12345")
                .balance(5000.50)
                .currency("USD")
                .isActive(true)
                .taxYear(2024)
                .build();

        var result = validator.validate(account);
        assertTrue(result.isValid(), result.message());
    }

    @Test
    void invalidTaxAccountShouldFail() {
        TaxAccount account = TaxAccount.builder()
                .accountId(99999L) // слишком маленький ID
                .taxpayerId("123") // короткий ID
                .balance(-100.0) // отрицательный баланс
                .currency("GBP") // недопустимая валюта
                .isActive(null) // null
                .taxYear(1800) // слишком старый
                .build();

        var result = validator.validate(account);
        assertFalse(result.isValid());
    }
}
