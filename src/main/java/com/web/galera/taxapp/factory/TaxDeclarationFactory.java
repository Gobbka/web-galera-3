package com.web.galera.taxapp.factory;

import com.web.galera.taxapp.comparator.TaxDeclarationComparators;
import com.web.galera.taxapp.datasource.cli.CliTaxDeclarationDataSource;
import com.web.galera.taxapp.datasource.random.RandomTaxDeclarationDataSource;
import com.web.galera.taxapp.entity.TaxDeclaration;
import com.web.galera.taxapp.repository.CliRepository;
import com.web.galera.taxapp.repository.JsonFileRepository;
import com.web.galera.taxapp.repository.RandomRepository;
import com.web.galera.taxapp.repository.Repository;
import com.web.galera.taxapp.ui.Prompter;

import java.io.File;
import java.util.Comparator;

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
                new File("tax-declaration.json")
        );
    }

    @Override
    public Comparator<TaxDeclaration> getComparator() {
        return TaxDeclarationComparators.byDeclarationType()
                .thenComparing(TaxDeclarationComparators.byTaxpayerId())
                .thenComparing(TaxDeclarationComparators.byTaxAmount());
    }
}
