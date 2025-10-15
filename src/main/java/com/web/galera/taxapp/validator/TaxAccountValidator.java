package com.web.galera.taxapp.validator;

import com.web.galera.taxapp.entity.TaxAccount;

import java.time.Year;
import java.util.Set;

public class TaxAccountValidator implements EntityValidator<TaxAccount> {

    private static final Set<String> ALLOWED_CURRENCIES = Set.of("RUB", "USD", "EUR");

    @Override
    public Result validate(TaxAccount entity) {
        if (!(entity instanceof TaxAccount account)) {
            return new Result(false, "Объект не является экземпляром TaxAccount");
        }

        if (account.getAccountId() == null || account.getAccountId() < 100000 || account.getAccountId() > 999999) {
            return new Result(false, "accountId должен быть в диапазоне 100000–999999");
        }

        if (account.getTaxpayerId() == null || account.getTaxpayerId().length() != 8) {
            return new Result(false, "taxpayerId должен содержать ровно 8 символов");
        }

        if (account.getBalance() == null || account.getBalance() < 0 || account.getBalance() > 10_000_000) {
            return new Result(false, "balance должен быть в диапазоне от 0 до 10 000 000");
        }

        if (account.getCurrency() == null || !ALLOWED_CURRENCIES.contains(account.getCurrency())) {
            return new Result(false, "currency должен быть одним из: RUB, USD, EUR");
        }

        if (account.getIsActive() == null) {
            return new Result(false, "isActive не может быть null");
        }

        int currentYear = Year.now().getValue();
        if (account.getTaxYear() == null || account.getTaxYear() < 1900 || account.getTaxYear() > currentYear) {
            return new Result(false, "taxYear должен быть в диапазоне 1900–" + currentYear);
        }

        return new Result(true, "TaxAccount прошёл валидацию успешно");
    }
}
