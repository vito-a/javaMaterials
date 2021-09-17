package ua.training;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<Employee> employeeList = new ArrayList<>();
    private Payroll payroll;

    void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    int getDepartmentSalary() {
        int salary = 0;

        for (Employee e : employeeList) {
            salary += e.getSalary();
        }

        return salary;
    }

    int getEmployeesCount() {
        return employeeList.size();
    }

    void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }
}
