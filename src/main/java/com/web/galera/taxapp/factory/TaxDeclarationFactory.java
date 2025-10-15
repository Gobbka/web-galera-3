package com.web.galera.taxapp.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.galera.taxapp.comparator.TaxDeclarationComparators;
import com.web.galera.taxapp.datasource.cli.CliTaxDeclarationDataSource;
import com.web.galera.taxapp.datasource.random.RandomTaxDeclarationDataSource;
import com.web.galera.taxapp.entity.TaxAccount;
import com.web.galera.taxapp.entity.TaxDeclaration;
import com.web.galera.taxapp.repository.CliRepository;
import com.web.galera.taxapp.repository.JsonFileRepository;
import com.web.galera.taxapp.repository.RandomRepository;
import com.web.galera.taxapp.repository.Repository;
import com.web.galera.taxapp.ui.Prompter;
import com.web.galera.taxapp.validator.EntityValidator;
import com.web.galera.taxapp.validator.TaxDeclarationValidator;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class TaxDeclarationFactory implements EntityFactory<TaxDeclaration> {

    @Override
    public Repository<TaxDeclaration> getRandomRepository() {
        return new RandomRepository<>(
                new RandomTaxDeclarationDataSource()
        );
    }

    @Override
    public Repository<TaxDeclaration> getCliRepository(Prompter prompter) {
        return new CliRepository<>(
                CliTaxDeclarationDataSource::read,
                prompter
        );
    }

    @Override
    public Repository<TaxDeclaration> getJsonFileRepository() {
        return new JsonFileRepository<>(
                new File("tax-declaration.json"),
                content -> {
                    try {
                        return new ObjectMapper().readValue(content, new TypeReference<List<TaxDeclaration>>() {});
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public Comparator<TaxDeclaration> getComparator() {
        return TaxDeclarationComparators.byDeclarationType()
                .thenComparing(TaxDeclarationComparators.byTaxpayerId())
                .thenComparing(TaxDeclarationComparators.byTaxAmount());
    }

    @Override
    public EntityValidator<TaxDeclaration> getValidator() {
        return new TaxDeclarationValidator();
    }
}
