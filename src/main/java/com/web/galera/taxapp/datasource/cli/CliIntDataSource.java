package com.web.galera.taxapp.datasource.cli;

import java.util.Scanner;

public class CliIntDataSource {
    public static Integer read(Scanner scanner) {
        System.out.print("Введите число: ");
        return scanner.nextInt();
    }
}
