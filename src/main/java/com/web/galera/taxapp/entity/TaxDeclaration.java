package com.web.galera.taxapp.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class TaxDeclaration {
    private final String declarationId;
    private final String taxpayerId;
    private final LocalDate submissionDate;
    private final Double declaredIncome;
    private final Double taxAmount;
    private final String declarationType;
    private final Boolean isApproved;
}