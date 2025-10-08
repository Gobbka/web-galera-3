package com.web.galera.taxapp.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaxDeclaration {
    private String declarationId;
    private String taxpayerId;
    private LocalDate submissionDate;
    private Double declaredIncome;
    private Double taxAmount;
    private String declarationType;
    private Boolean isApproved;
}