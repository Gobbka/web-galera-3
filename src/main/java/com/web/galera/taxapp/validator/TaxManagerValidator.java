package com.web.galera.taxapp.validator;

import com.web.galera.taxapp.entity.TaxManager;

import java.util.Set;

public class TaxManagerValidator implements EntityValidator<TaxManager> {

    private static final Set<String> ALLOWED_DEPARTMENTS = Set.of(
            "Налоговый контроль", "Аудит", "Аналитика", "Бухгалтерия"
    );

    @Override
    public Result validate(TaxManager entity) {
        if (!(entity instanceof TaxManager manager)) {
            return new Result(false, "Объект не является экземпляром TaxManager");
        }

        // managerId: 1000–9999
        if (manager.getManagerId() == null || manager.getManagerId() < 1000 || manager.getManagerId() > 9999) {
            return new Result(false, "managerId должен быть в диапазоне 1000–9999");
        }

        // firstName: не null и не пустая строка
        if (manager.getFirstName() == null || manager.getFirstName().isBlank()) {
            return new Result(false, "firstName не может быть null или пустым");
        }

        // lastName: не null и не пустая строка
        if (manager.getLastName() == null || manager.getLastName().isBlank()) {
            return new Result(false, "lastName не может быть null или пустым");
        }

        // department: одно из допустимых
        if (manager.getDepartment() == null || !ALLOWED_DEPARTMENTS.contains(manager.getDepartment())) {
            return new Result(false, "department должен быть одним из: " + ALLOWED_DEPARTMENTS);
        }

        // yearsOfExperience: не null и > 0
        if (manager.getYearsOfExperience() == null || manager.getYearsOfExperience() <= 0) {
            return new Result(false, "yearsOfExperience должен быть положительным числом");
        }

        // salary: не null и > 0
        if (manager.getSalary() == null || manager.getSalary() <= 0) {
            return new Result(false, "salary должна быть положительным числом");
        }

        // isSenior: не может быть null
        if (manager.getIsSenior() == null) {
            return new Result(false, "isSenior не может быть null");
        }

        return new Result(true, "TaxManager прошёл валидацию успешно");
    }
}
