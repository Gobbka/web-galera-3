package com.web.galera.taxapp.factory;

import com.web.galera.taxapp.comparator.TaxAccountComparators;
import com.web.galera.taxapp.comparator.TaxDeclarationComparators;
import com.web.galera.taxapp.datasource.cli.CliTaxDeclarationDataSource;
import com.web.galera.taxapp.datasource.random.RandomTaxDeclarationDataSource;
import com.web.galera.taxapp.entity.TaxDeclaration;
import com.web.galera.taxapp.repository.CliRepository;
import com.web.galera.taxapp.repository.JsonFileRepository;
import com.web.galera.taxapp.repository.RandomRepository;
import com.web.galera.taxapp.repository.Repository;

import java.io.File;
import java.util.Comparator;
import java.util.Scanner;

public class TaxDeclarationFactory implements EntityFactory<TaxDeclaration> {

    @Override
    public Repository<TaxDeclaration> getRandomRepository() {
        return new RandomRepository<>(
                new RandomTaxDeclarationDataSource()
        );
    }

    @Override
    public Repository<TaxDeclaration> getCliRepository(Scanner scanner) {
        return new CliRepository<>(
                CliTaxDeclarationDataSource::read,
                scanner
        );
    }

    @Override
    public Repository<TaxDeclaration> getJsonFileRepository(String filename) {
        return new JsonFileRepository<>(
                new File(filename)
        );
    }

    @Override
    public Comparator<TaxDeclaration> getComparator() {
        return TaxDeclarationComparators.byDeclarationType()
                .thenComparing(TaxDeclarationComparators.byTaxpayerId())
                .thenComparing(TaxDeclarationComparators.byTaxAmount());
    }
}
