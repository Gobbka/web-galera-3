package com.web.galera.taxapp.validator;

import com.web.galera.taxapp.entity.TaxManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxManagerValidatorTest {

    private final TaxManagerValidator validator = new TaxManagerValidator();

    @Test
    void validManagerShouldPass() {
        TaxManager manager = TaxManager.builder()
                .managerId(1234)
                .firstName("Иван")
                .lastName("Иванов")
                .department("Аудит")
                .yearsOfExperience(10)
                .salary(120_000.0)
                .isSenior(false)
                .build();

        var result = validator.validate(manager);
        assertTrue(result.isValid(), result.message());
    }

    @Test
    void invalidManagerShouldFail() {
        TaxManager manager = TaxManager.builder()
                .managerId(99) // вне диапазона
                .firstName("") // пустая строка
                .lastName(null) // null
                .department("Продажи") // нет в списке
                .yearsOfExperience(0) // ноль
                .salary(0.0) // ноль
                .isSenior(null) // null
                .build();

        var result = validator.validate(manager);
        assertFalse(result.isValid());
    }
}
