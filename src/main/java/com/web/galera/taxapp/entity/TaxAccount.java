package com.web.galera.taxapp.entity;
import java.util.Objects;

public class TaxAccount {
    private final Long accountId;
    private final String taxpayerId;
    private final Double balance;
    private final String currency;
    private final Boolean isActive;
    private final Integer taxYear;

    private TaxAccount(Builder builder) {
        this.accountId = builder.accountId;
        this.taxpayerId = builder.taxpayerId;
        this.balance = builder.balance;
        this.currency = builder.currency;
        this.isActive = builder.isActive;
        this.taxYear = builder.taxYear;
    }

    // Getters
    public Long getAccountId() { return accountId; }
    public String getTaxpayerId() { return taxpayerId; }
    public Double getBalance() { return balance; }
    public String getCurrency() { return currency; }
    public Boolean getIsActive() { return isActive; }
    public Integer getTaxYear() { return taxYear; }

    // Builder class
    public static class Builder {
        private Long accountId;
        private String taxpayerId;
        private Double balance;
        private String currency;
        private Boolean isActive;
        private Integer taxYear;

        public Builder accountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder taxpayerId(String taxpayerId) {
            this.taxpayerId = taxpayerId;
            return this;
        }

        public Builder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder taxYear(Integer taxYear) {
            this.taxYear = taxYear;
            return this;
        }

        public TaxAccount build() {
            return new TaxAccount(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxAccount that = (TaxAccount) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(taxpayerId, that.taxpayerId) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(taxYear, that.taxYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, taxpayerId, balance, currency, isActive, taxYear);
    }

    @Override
    public String toString() {
        return "TaxAccount{" +
                "accountId=" + accountId +
                ", taxpayerId='" + taxpayerId + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", isActive=" + isActive +
                ", taxYear=" + taxYear +
                '}';
    }
}