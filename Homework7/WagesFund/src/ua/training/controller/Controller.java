package ua.training.controller;

import ua.training.model.*;
import ua.training.view.View;

import java.text.ParseException;
import java.util.Date;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processApp() throws ParseException {
        Employee employee1 = new Employee("Podoprygora",
                "Taras",
                "Oleksijovich",
                "podoprygora.taras2021@gmail.com",
                "2021-03-06", "1994-05-22",
                1000, 100, EmployeeTypes.WORKER);

        Employee employee2 = new Employee();
        employee2.setLastName("Omelchenko");
        employee2.setFirstName("Petro");
        employee2.setFamilyName("Oleksandrovych");
        employee2.setEmail("omelchenko.petro2021@gmail.com");

        try {
            employee2.setCreatedDate("2021-07-17");
        } catch (ParseException e) {
            view.printDateParsingError("2021-07-17");
        }

        try {
            employee2.setBirthday("1990-09-06");
        } catch (ParseException e) {
            view.printDateParsingError("1990-09-06");
        }

        employee2.setBonus(200);
        employee2.setType(EmployeeTypes.MANAGER);
        employee2.setBaseSalary(2000);

        Department department1 = new Department("Manufacturing Department");
        department1.addEmployee(employee1);
        department1.addEmployee(employee2);

        Payroll payroll1 = new Payroll(3500, PayrollTypes.EQUAL);
        department1.setPayroll(payroll1);

        view.printDepartmentSalary(department1);

        Payroll payroll2 = new Payroll(4000);
        payroll2.setPayrollType(PayrollTypes.PROPORTIONAL);

        Department department2 = new Department("Sales department");
        department2.addEmployee(employee1);
        department2.addEmployee(employee2);
        department2.setPayroll(payroll2);

        view.printDepartmentSalary(department2);
    }
}