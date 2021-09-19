package ua.training;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<Employee> employeeList = new ArrayList<>();
    private String departmentName;
    private Payroll payroll;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    long getDepartmentSalary() {
        long salary = 0;

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
