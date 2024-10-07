package com.java.collections;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class SummaryStatistics {

    public static void main(String[] args) {
        List<Employee> employees = Employee.getDummyEmployees();

        IntSummaryStatistics summary = employees.stream().mapToInt(x -> x.age).summaryStatistics();

        System.out.println(summary.getMax());
        System.out.println(summary.getMin());
        System.out.println(summary.getAverage());
        System.out.println(summary.getSum());
        System.out.println(summary.getCount());

        // Get the second youngest employee

        List<Integer> ages = employees.stream().map(e -> e.age).sorted().toList();
        System.out.println(ages.stream().skip(1).limit(2).toList());

        // join the strings
        String names = employees.stream()
                .map(e -> e.name.toUpperCase())
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(names);
    }
}
