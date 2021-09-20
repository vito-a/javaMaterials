package ua.training.model;

import ua.training.model.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager extends Employee {
    private List<Employee> employeeList = new ArrayList<>();

    void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
