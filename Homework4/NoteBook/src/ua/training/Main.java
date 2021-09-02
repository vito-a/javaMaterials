/*
 * Java Autumn 2021 - 04. String processing + Wrapper classes - NoteBook
 *
 * Main
 *
 */
package ua.training;

import ua.training.controller.Controller;
import ua.training.model.Model;
import ua.training.view.View;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Controller controller =
                new Controller(new Model(), new View());
        // Run
        controller.processUser();
    }
}
