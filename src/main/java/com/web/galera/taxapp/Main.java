package com.web.galera.taxapp;

import com.web.galera.taxapp.factory.EntityFactory;
import com.web.galera.taxapp.factory.TaxAccountFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var entityMap = new HashMap<String, EntityFactory<?>>();
        entityMap.put("tax-account", new TaxAccountFactory());

    }

}
