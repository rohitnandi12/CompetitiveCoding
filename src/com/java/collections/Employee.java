package com.java.collections;

import java.util.List;

public class Employee {
    int id;
    String name;
    int age;

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static List<Employee> getDummyEmployees() {
        return List.of(
                new Employee(1, "Pikolo", 31),
                new Employee(2, "Goku", 32),
                new Employee(2, "Goku", 40),
                new Employee(3, "Gohan", 25),
                new Employee(4, "Vegetta", 25)
        );
    }
}

class Department {

    String name;
    List<Employee> employees;

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    static List<Department> getDepartment() {
        return List.of(
                new Department("Dept1", Employee.getDummyEmployees()),
                new Department("Dept2", Employee.getDummyEmployees())
        );
    }
}