/*
 * Java Autumn 2021 - 08. Exceptions  Basic IO - NoteBook
 *
 * Controller
 *
 */
package ua.training.controller;

import ua.training.model.LoginExistsException;
import ua.training.model.Model;
import ua.training.view.View;

import java.util.Scanner;

/**
 *
 */
public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        boolean isValidNote = false;
        Scanner sc = new Scanner(System.in);
        InputNoteNoteBook inputNoteNoteBook =
                new InputNoteNoteBook(view, sc, model);

        inputNoteNoteBook.inputNote();

        while (!isValidNote) {
            try {
                isValidNote = model.validateFields();
            } catch (LoginExistsException e) {
                e.printStackTrace();
                System.out.println("Login exists " + e.getLoginData());
                inputNoteNoteBook.inputLogin();
            }
        }
    }
}
