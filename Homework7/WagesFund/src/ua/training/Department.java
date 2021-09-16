package ua.training;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<Employee> employeeList = new ArrayList<>();
    Payroll payroll;

    void addEmployee (Employee employee) {
        employeeList.add(employee);
    }
}
