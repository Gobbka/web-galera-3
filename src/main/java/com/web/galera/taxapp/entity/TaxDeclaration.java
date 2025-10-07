package com.web.galera.taxapp.entity;

import java.time.LocalDate;
import java.util.Objects;

public class TaxDeclaration {
    private final String declarationId;
    private final String taxpayerId;
    private final LocalDate submissionDate;
    private final Double declaredIncome;
    private final Double taxAmount;
    private final String declarationType;
    private final Boolean isApproved;

    private TaxDeclaration(Builder builder) {
        this.declarationId = builder.declarationId;
        this.taxpayerId = builder.taxpayerId;
        this.submissionDate = builder.submissionDate;
        this.declaredIncome = builder.declaredIncome;
        this.taxAmount = builder.taxAmount;
        this.declarationType = builder.declarationType;
        this.isApproved = builder.isApproved;
    }

    // Getters
    public String getDeclarationId() { return declarationId; }
    public String getTaxpayerId() { return taxpayerId; }
    public LocalDate getSubmissionDate() { return submissionDate; }
    public Double getDeclaredIncome() { return declaredIncome; }
    public Double getTaxAmount() { return taxAmount; }
    public String getDeclarationType() { return declarationType; }
    public Boolean getIsApproved() { return isApproved; }

    // Builder class
    public static class Builder {
        private String declarationId;
        private String taxpayerId;
        private LocalDate submissionDate;
        private Double declaredIncome;
        private Double taxAmount;
        private String declarationType;
        private Boolean isApproved;

        public Builder declarationId(String declarationId) {
            this.declarationId = declarationId;
            return this;
        }

        public Builder taxpayerId(String taxpayerId) {
            this.taxpayerId = taxpayerId;
            return this;
        }

        public Builder submissionDate(LocalDate submissionDate) {
            this.submissionDate = submissionDate;
            return this;
        }

        public Builder declaredIncome(Double declaredIncome) {
            this.declaredIncome = declaredIncome;
            return this;
        }

        public Builder taxAmount(Double taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public Builder declarationType(String declarationType) {
            this.declarationType = declarationType;
            return this;
        }

        public Builder isApproved(Boolean isApproved) {
            this.isApproved = isApproved;
            return this;
        }

        public TaxDeclaration build() {
            return new TaxDeclaration(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxDeclaration that = (TaxDeclaration) o;
        return Objects.equals(declarationId, that.declarationId) &&
                Objects.equals(taxpayerId, that.taxpayerId) &&
                Objects.equals(submissionDate, that.submissionDate) &&
                Objects.equals(declaredIncome, that.declaredIncome) &&
                Objects.equals(taxAmount, that.taxAmount) &&
                Objects.equals(declarationType, that.declarationType) &&
                Objects.equals(isApproved, that.isApproved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declarationId, taxpayerId, submissionDate, declaredIncome,
                taxAmount, declarationType, isApproved);
    }

    @Override
    public String toString() {
        return "TaxDeclaration{" +
                "declarationId='" + declarationId + '\'' +
                ", taxpayerId='" + taxpayerId + '\'' +
                ", submissionDate=" + submissionDate +
                ", declaredIncome=" + declaredIncome +
                ", taxAmount=" + taxAmount +
                ", declarationType='" + declarationType + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }
}