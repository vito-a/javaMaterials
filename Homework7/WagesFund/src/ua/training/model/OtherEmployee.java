package ua.training.model;

import ua.training.model.Employee;

import java.util.Date;

public class OtherEmployee extends Employee {
    String position;

    public OtherEmployee(String lastName, String firstName, String familyName, String email, Date createdDate, int salary, EmployeeTypes type) {
        super(lastName, firstName, familyName, email, createdDate, salary, type);
    }
}
