package com.web.galera.taxapp.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class TaxAccount {
    private final Long accountId;
    private final String taxpayerId;
    private final Double balance;
    private final String currency;
    private final Boolean isActive;
    private final Integer taxYear;
}