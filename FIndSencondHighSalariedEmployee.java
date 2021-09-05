package com.gap.gid.jesie.bean;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FIndSencondHighSalariedEmployee {

    static class Employee {
        private String name;
        private Double salary;

        public Employee(String name, Double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public Double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return name.equals(employee.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee("one", 10000.0));
        list.add(new Employee("two", 10001.0));
        list.add(new Employee("three", 9999.0));
        list.add(new Employee("four", 10002.0));
        list.add(new Employee("four", 9998.0));

        Employee secondHighSalaried = list.stream()
                .filter(e -> e != Collections.max(list, Comparator.comparing(Employee::getSalary)))
                .max(Comparator.comparing(Employee::getSalary)).orElse(null);

        System.out.println(secondHighSalaried);


        //finding distinct obj in list
        List<Employee> testList = list.stream().distinct().collect(Collectors.toList());

        //adding distinct obj as key
        Map<Employee, String> map = list.stream().distinct()
                .collect(Collectors.toMap(Function.identity(), Employee::getName));

        System.out.println(testList);

        System.out.println(map);


        //use for override hashcode()
        System.out.println(map.get(new Employee("four", 0.0)));
    }
}
