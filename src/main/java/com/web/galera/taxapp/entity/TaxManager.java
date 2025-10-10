package com.web.galera.taxapp.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class TaxManager implements Entity {
    private final Integer managerId;
    private final String firstName;
    private final String lastName;
    private final String department;
    private final Integer yearsOfExperience;
    private final Double salary;
    private final Boolean isSenior;
}