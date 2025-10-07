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
    private  Long accountId;
    private  String taxpayerId;
    private  Double balance;
    private  String currency;
    private  Boolean isActive;
    private  Integer taxYear;
}