package com.web.galera.taxapp.validator;

public interface EntityValidator<TEntity> {

    Result validate(TEntity entity);

    record Result(boolean isValid, String message) {
    }
}
