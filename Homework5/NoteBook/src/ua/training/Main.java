/*
 * Java Autumn 2021 - 08. Exceptions  Basic IO - NoteBook
 *
 * Main
 *
 */
package ua.training;

import ua.training.controller.Controller;
import ua.training.model.LoginExistsException;
import ua.training.model.Model;
import ua.training.view.View;

public class Main {

    public static void main(String[] args) throws LoginExistsException {
	// write your code here
        Controller controller =
                new Controller(new Model(), new View());
        controller.processUser();
    }
}
