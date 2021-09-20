package ua.training;

import ua.training.controller.Controller;
import ua.training.model.Model;
import ua.training.view.View;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
       Model model = new Model();
       View view = new View();

       Controller controller = new Controller(model, view);
        try {
            controller.processApp();
        } catch (ParseException e) {
            view.printDateParsingError("");
        }
    }
}
