package com.web.galera.taxapp.factory;

import com.web.galera.taxapp.comparator.TaxAccountComparators;
import com.web.galera.taxapp.datasource.cli.CliTaxAccountDataSource;
import com.web.galera.taxapp.datasource.random.RandomTaxAccountDataSource;
import com.web.galera.taxapp.entity.TaxAccount;
import com.web.galera.taxapp.repository.CliRepository;
import com.web.galera.taxapp.repository.JsonFileRepository;
import com.web.galera.taxapp.repository.RandomRepository;
import com.web.galera.taxapp.repository.Repository;

import java.io.File;
import java.util.Comparator;
import java.util.Scanner;

public class TaxAccountFactory implements EntityFactory<TaxAccount> {
    @Override
    public Repository<TaxAccount> getRandomRepository() {
        return new RandomRepository<>(
                new RandomTaxAccountDataSource()
        );
    }

    @Override
    public Repository<TaxAccount> getCliRepository(Scanner scanner) {
        return new CliRepository<>(
                CliTaxAccountDataSource::read,
                scanner
        );
    }

    @Override
    public Repository<TaxAccount> getJsonFileRepository(String filename) {
        return new JsonFileRepository<>(
                new File(filename)
        );
    }

    @Override
    public Comparator<TaxAccount> getComparator() {
        return TaxAccountComparators.byCurrency()
                .thenComparing(TaxAccountComparators.byBalance())
                .thenComparing(TaxAccountComparators.byTaxYear());
    }
}
