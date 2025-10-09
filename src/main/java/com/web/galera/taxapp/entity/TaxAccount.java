package com.web.galera.taxapp.entity;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaxAccount {
    private Long accountId;
    private String taxpayerId;
    private Double balance;
    private String currency;
    private Boolean isActive;
    private Integer taxYear;
}