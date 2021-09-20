package ua.training.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<Employee> employeeList = new ArrayList<>();
    private String departmentName;
    private Payroll payroll;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    long getBaseSalary() {
        long salary = 0;

        for (Employee e : employeeList) {
            salary += e.getSalary();
        }

        return salary;
    }

    public long getDepartmentSalary() {
        long salary = 0;

        for (Employee e : employeeList) {
            salary += this.payroll.getSalary(this, e);
        }

        return salary;
    }

    public long getEmployeeSalary(Employee employee) {
        return this.payroll.getSalary(this, employee);
    }

    int getEmployeesCount() {
        return employeeList.size();
    }

    public List<Employee> getEmployeesList() { return  this.employeeList; }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }
}
