package com.web.galera.taxapp.util;

import lombok.RequiredArgsConstructor;
import java.util.Scanner;

@RequiredArgsConstructor
public class CliPrompter {

    private final Scanner scanner;

    public String askString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public long askLong(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    public double askDouble(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число с плавающей точкой.");
            }
        }
    }

    public boolean askBoolean(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("t") || input.equals("1")) return true;
            if (input.equals("false") || input.equals("f") || input.equals("0")) return false;
            System.out.println("Ошибка: введите true/false.");
        }
    }

    public int askInt(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }
}
