package com.web.galera.taxapp.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class TaxManager {
    private  Integer managerId;
    private  String firstName;
    private  String lastName;
    private  String department;
    private  Integer yearsOfExperience;
    private  Double salary;
    private  Boolean isSenior;
}