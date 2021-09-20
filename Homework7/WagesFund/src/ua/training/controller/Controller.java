package ua.training.controller;

import ua.training.model.*;
import ua.training.view.View;

import java.util.Date;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processApp() {
        Employee employee1 = new Employee("Podoprygora",
                "Taras",
                "Oleksijovich",
                "podoprygora.taras2021@gmail.com",
                new Date("06/03/2021"), 1000, EmployeeTypes.WORKER);

        Employee employee2 = new Employee();
        employee2.setLastName("Omelchenko");
        employee2.setFirstName("Petro");
        employee2.setFamilyName("Oleksandrovych");
        employee2.setEmail("omelchenko.petro2021@gmail.com");
        employee2.setCreatedDate(new Date("17/07/2021"));
        employee2.setType(EmployeeTypes.MANAGER);
        employee2.setSalary(2000);

        Department department1 = new Department("Manufacturing Department");
        department1.addEmployee(employee1);
        department1.addEmployee(employee2);

        Payroll payroll1 = new Payroll(3500, PayrollTypes.EQUAL);
        department1.setPayroll(payroll1);

        view.printDepartmentSalary(department1);

        Payroll payroll2 = new Payroll(3500);
        payroll2.setPayrollType(PayrollTypes.PROPORTIONAL);

        Department department2 = new Department("Sales department");
        department2.addEmployee(employee1);
        department2.addEmployee(employee2);
        department2.setPayroll(payroll2);

        view.printDepartmentSalary(department2);
    }
}