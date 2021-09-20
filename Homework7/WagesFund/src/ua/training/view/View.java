package ua.training.view;

import ua.training.model.Department;
import ua.training.model.Employee;

public class View {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printDepartmentSalary(Department department) {
        printMessage("");

        printMessage("Salary for " + department.getDepartmentName() +" is: " + department.getDepartmentSalary());

        for (Employee e : department.getEmployeesList()) {
            printMessage("Salary for " + e.getFullName() + " is: " + department.getEmployeeSalary(e));
        }
    }

    public void printDateParsingError(String dateString) {
        String message = "Incorrect date format";
        printMessage("");

        if (dateString.length() > 0) {
            message.concat(": " + dateString);
        }

        printMessage(message);
    }
}