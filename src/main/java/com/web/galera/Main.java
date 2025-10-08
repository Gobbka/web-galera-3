package com.web.galera;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.galera.taxapp.datasource.cli.CliIntDataSource;
import com.web.galera.taxapp.entity.TaxAccount;
import com.web.galera.taxapp.repository.CliRepository;
import com.web.galera.taxapp.repository.Repository;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        var scanner = new Scanner(System.in);

        Repository<Integer> repository = new CliRepository<>(
                CliIntDataSource::read,
                scanner
        );

        ObjectMapper mapper = new ObjectMapper();
        var list = mapper.readValue("[{\"accountId\": 228}]", new TypeReference<List<TaxAccount>>() {});
        System.out.println(list);

        repository.getList(4).forEach(System.out::println);

    }

}
