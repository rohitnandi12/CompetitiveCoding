package com.java.collections;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListOperations {
    public static void main(String[] args) {
        List<Employee> employees = Employee.getDummyEmployees();

        // Get the second youngest employee

        List<Integer> ages = employees.stream().map(e -> e.age).sorted().toList();
        System.out.println(ages.stream().skip(1).limit(2).toList());

        // join the strings
        String names = employees.stream()
                .map(e -> e.name.toUpperCase())
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(names);

        List<String> allNames = employees.stream().map(e -> e.name).toList();
        // find duplicate
        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = allNames.stream()
                .filter(name -> !uniqueNames.add(name))
                .collect(Collectors.toSet());
        System.out.println(uniqueNames);
        System.out.println(duplicateNames);

        // Another way finding duplicate
        Map<String, Long> mapOfNames = allNames.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(mapOfNames);

        Set<String> duplicateNames2 = mapOfNames.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println(duplicateNames2);

        System.out.println(
                allNames.stream().filter(name -> Collections.frequency(allNames, name) > 1)
                        .collect(Collectors.toSet())
        );

        // encounter order depends on the stream and collection
        // example: for Set it will be random and LinkedList it will be sequential
        System.out.println(allNames.stream().findFirst()); // deterministic, depends on the encounter order
        System.out.println(allNames.parallelStream().findFirst()); // same
        System.out.println(allNames.stream().findAny()); // same
        System.out.println(allNames.parallelStream().findAny()); // non-deterministic


        System.out.println(allNames.parallelStream().anyMatch(n -> n.contains("a")));
        System.out.println(allNames.parallelStream().allMatch(n -> n.contains("a")));
        System.out.println(allNames.parallelStream().noneMatch(n -> n.contains("a")));



        // second smallest - no duplicates
        System.out.println(employees.stream().sorted(Comparator.comparingInt(f -> f.age)).skip(1).findFirst());

        // second smallest - with duplicates
        System.out.println(employees.stream().distinct()
                .sorted(Comparator.comparingInt(f -> f.age)).skip(1).findFirst());

        System.out.println(employees.stream().skip(100).toList()); // no exception on outOfBound

        System.out.println(employees.stream().limit(100).toList()); // no exception on outOfBound

        // boxed, Boxing Auto-Boxing
        int[] arr = new int[]{1,2,3,4,5,6};
        System.out.println(Arrays.stream(arr).boxed().toList());

        // flatmap
        List<Department> departments = Department.getDepartment();
        List<Employee> allEmployeesAcrossDepartments = departments
                .stream()
                .flatMap(department -> department.employees.stream())
                .toList();

        System.out.println(allEmployeesAcrossDepartments);

        // peek - doesn't modify the element, useful for logging or debugging
        System.out.println(
                employees.stream().peek(e -> System.out.println("Processing Employee " + e.name))
                        .map(e -> e.name).toList()
        );

    }
}
