package com.java.collections;

import java.util.Optional;

public class OptionalExamples {
    public static void main(String[] args) {
        Optional<Employee> employee = Optional.of(Employee.getDummyEmployees().get(0));
        Optional<String> optionalName = Optional.ofNullable(employee.get().name);

        System.out.println(optionalName.orElse("Anonymous"));

        System.out.println(optionalName.orElseGet(() -> "Anonymous"));

        System.out.println(optionalName.orElseThrow(IllegalArgumentException::new));

        optionalName.ifPresent(name -> System.out.println(name + " is present"));
        optionalName.ifPresentOrElse(
                name -> System.out.println(name + " is present"),
                () -> System.out.println("Name is not present")
        );
    }
}
