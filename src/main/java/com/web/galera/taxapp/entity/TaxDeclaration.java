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
    private  String declarationId;
    private  String taxpayerId;
    private  LocalDate submissionDate;
    private  Double declaredIncome;
    private  Double taxAmount;
    private  String declarationType;
    private  Boolean isApproved;
}