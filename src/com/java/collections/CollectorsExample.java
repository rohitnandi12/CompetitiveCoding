package com.java.collections;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectorsExample {

    public static void main(String[] args) {

        List<Employee> employees = Employee.getDummyEmployees();

        Map<Boolean, List<Employee>> partitionByAge = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.age > 30));
        System.out.println(partitionByAge);

        // totalEmployees
        System.out.println(employees.stream().collect(Collectors.counting()));
        System.out.println((Long) employees.stream().count());
        System.out.println(employees.size());

        IntSummaryStatistics stats = employees.stream()
                .collect(Collectors.summarizingInt(e -> e.age));

        System.out.println(stats);
        System.out.println(stats.getMax());
        System.out.println(stats.getAverage());


        // Collectors.mapping
        List<Integer> nameLength = employees.stream()
                .collect(Collectors.mapping(e -> e.name.length(), Collectors.toList()));

        System.out.println(nameLength);

        System.out.println(employees.stream().map(e -> e.name)
                .collect(Collectors.joining(", ", "[", "]")));
    }
}
