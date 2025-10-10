package com.web.galera.taxapp.factory;

import com.web.galera.taxapp.comparator.TaxManagerComparators;
import com.web.galera.taxapp.datasource.cli.CliTaxManagerDataSource;
import com.web.galera.taxapp.datasource.random.RandomTaxManagerDataSource;
import com.web.galera.taxapp.entity.TaxManager;
import com.web.galera.taxapp.repository.CliRepository;
import com.web.galera.taxapp.repository.JsonFileRepository;
import com.web.galera.taxapp.repository.RandomRepository;
import com.web.galera.taxapp.repository.Repository;

import java.io.File;
import java.util.Comparator;
import java.util.Scanner;

public class TaxManagerFactory implements EntityFactory<TaxManager> {

    @Override
    public Repository<TaxManager> getRandomRepository() {
        return new RandomRepository<>(
                new RandomTaxManagerDataSource()
        );
    }

    @Override
    public Repository<TaxManager> getCliRepository(Scanner scanner) {
        return new CliRepository<>(
                CliTaxManagerDataSource::read,
                scanner
        );
    }

    @Override
    public Repository<TaxManager> getJsonFileRepository(String filename) {
        return new JsonFileRepository<>(
                new File(filename)
        );
    }

    @Override
    public Comparator<TaxManager> getComparator() {
        return TaxManagerComparators.byDepartment()
                .thenComparing(TaxManagerComparators.bySalary())
                .thenComparing(TaxManagerComparators.byFullName());
    }
}