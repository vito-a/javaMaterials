package ua.training;

import ua.training.controller.Controller;
import ua.training.model.Department;
import ua.training.model.Employee;
import ua.training.model.Payroll;
import ua.training.model.PayrollTypes;
import ua.training.model.Model;
import ua.training.view.View;

public class Main {

    public static void main(String[] args) {
       Model model = new Model();
       View view = new View();

       Controller controller = new Controller(model, view);
       controller.processApp();
    }
}
