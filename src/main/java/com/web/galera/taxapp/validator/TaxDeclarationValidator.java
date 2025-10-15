package com.web.galera.taxapp.validator;

import com.web.galera.taxapp.entity.TaxDeclaration;

import java.time.LocalDate;
import java.util.Set;

public class TaxDeclarationValidator implements EntityValidator<TaxDeclaration> {

    private static final Set<String> ALLOWED_DECLARATION_TYPES = Set.of("НДФЛ", "НДС", "ЕСН", "Акцизы");

    @Override
    public Result validate(TaxDeclaration entity) {
        if (!(entity instanceof TaxDeclaration declaration)) {
            return new Result(false, "Объект не является экземпляром TaxDeclaration");
        }

        // declarationId: UUID усечённый до 8 символов
        if (declaration.getDeclarationId() == null || declaration.getDeclarationId().length() != 8) {
            return new Result(false, "declarationId должен содержать ровно 8 символов");
        }

        // taxpayerId: UUID усечённый до 8 символов
        if (declaration.getTaxpayerId() == null || declaration.getTaxpayerId().length() != 8) {
            return new Result(false, "taxpayerId должен содержать ровно 8 символов");
        }

        // submissionDate: от 2000 года до текущей даты включительно
        if (declaration.getSubmissionDate() == null) {
            return new Result(false, "submissionDate не может быть null");
        }
        LocalDate minDate = LocalDate.of(2000, 1, 1);
        LocalDate maxDate = LocalDate.now();
        if (declaration.getSubmissionDate().isBefore(minDate) || declaration.getSubmissionDate().isAfter(maxDate)) {
            return new Result(false, "submissionDate должна быть в диапазоне от 2000-01-01 до текущей даты");
        }

        if (declaration.getDeclaredIncome() == null ||
                declaration.getDeclaredIncome() < 0 ||
                declaration.getDeclaredIncome() > 10_000_000) {
            return new Result(false, "declaredIncome должен быть в диапазоне 0–10 000 000");
        }

        if (declaration.getTaxAmount() == null ||
                declaration.getTaxAmount() < 0 ||
                declaration.getTaxAmount() > 1_000_000) {
            return new Result(false, "taxAmount должен быть в диапазоне 0–1 000 000");
        }

        if (declaration.getDeclarationType() == null ||
                !ALLOWED_DECLARATION_TYPES.contains(declaration.getDeclarationType())) {
            return new Result(false, "declarationType должен быть одним из: НДФЛ, НДС, ЕСН, Акцизы");
        }

        if (declaration.getIsApproved() == null) {
            return new Result(false, "isApproved не может быть null");
        }

        return new Result(true, "TaxDeclaration прошла валидацию успешно");
    }
}
