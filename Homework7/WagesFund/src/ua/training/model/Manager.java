package ua.training.model;

import ua.training.model.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager extends Employee {
    private List<Employee> employeeList = new ArrayList<>();

    public Manager(String lastName, String firstName, String familyName, String email, Date createdDate, int salary, EmployeeTypes type) {
        super(lastName, firstName, familyName, email, createdDate, salary, type);
    }

    void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
