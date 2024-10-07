package com.java.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupBy {

    public static void main(String[] args) {

        List<Employee> employees = Employee.getDummyEmployees();
        // unsorted map
        Map<Integer, List<Employee>> groupByAge = employees.stream().collect(
                Collectors.groupingBy(emp -> emp.age));
        System.out.println(groupByAge);

        // still unordered, but unique list
        Map<Integer, Set<Employee>> groupByAgeAsSet = employees.stream().collect(
                Collectors.groupingBy(emp -> emp.age, Collectors.toSet()));
        System.out.println(groupByAgeAsSet);

        Map<Integer, Set<Employee>> groupByAgeAsSetAndOrdered = employees.stream().collect(
                Collectors.groupingBy(emp -> emp.age, TreeMap::new, Collectors.toSet()));
        System.out.println(groupByAgeAsSetAndOrdered);

        // group by and downstream operations
        Map<Integer, Long> ageCount = employees.stream().collect(
                Collectors.groupingBy(emp -> emp.age, Collectors.counting()));
        System.out.println(ageCount);

        // filtering
        // filter is intermediate operations, filtering is used in terminal operations
        // at the end, along with a terminal operation
        List<Employee> seniorEmployees = employees.stream().collect(
                Collectors.filtering(emp -> emp.age > 30, Collectors.toList()));
        System.out.println(seniorEmployees);

        // collectingAndThen, first collect and then apply the function
        Integer uniqueEmployeeCount = employees.stream()
                .collect(Collectors.collectingAndThen(Collectors.mapping(e -> e.name, Collectors.toSet()),
                        Set::size));
        System.out.println(uniqueEmployeeCount);

        // toMap
        // toConcurrentMap
        // reducing
        // flatMapping

    }

}
