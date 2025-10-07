package com.web.galera.taxapp.entity;
import java.util.Objects;

public class TaxManager {
    private final Integer managerId;
    private final String firstName;
    private final String lastName;
    private final String department;
    private final Integer yearsOfExperience;
    private final Double salary;
    private final Boolean isSenior;

    private TaxManager(Builder builder) {
        this.managerId = builder.managerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.department = builder.department;
        this.yearsOfExperience = builder.yearsOfExperience;
        this.salary = builder.salary;
        this.isSenior = builder.isSenior;
    }

    // Getters
    public Integer getManagerId() { return managerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDepartment() { return department; }
    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public Double getSalary() { return salary; }
    public Boolean getIsSenior() { return isSenior; }

    // Builder class
    public static class Builder {
        private Integer managerId;
        private String firstName;
        private String lastName;
        private String department;
        private Integer yearsOfExperience;
        private Double salary;
        private Boolean isSenior;

        public Builder managerId(Integer managerId) {
            this.managerId = managerId;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder yearsOfExperience(Integer yearsOfExperience) {
            this.yearsOfExperience = yearsOfExperience;
            return this;
        }

        public Builder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Builder isSenior(Boolean isSenior) {
            this.isSenior = isSenior;
            return this;
        }

        public TaxManager build() {
            return new TaxManager(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxManager that = (TaxManager) o;
        return Objects.equals(managerId, that.managerId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(department, that.department) &&
                Objects.equals(yearsOfExperience, that.yearsOfExperience) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(isSenior, that.isSenior);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, firstName, lastName, department,
                yearsOfExperience, salary, isSenior);
    }

    @Override
    public String toString() {
        return "TaxManager{" +
                "managerId=" + managerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", salary=" + salary +
                ", isSenior=" + isSenior +
                '}';
    }
}