/*
 * Java Autumn 2021 - 04. String processing + Wrapper classes - NoteBook
 *
 * Controller
 *
 */
package ua.training.controller;

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
        Scanner sc = new Scanner(System.in);
        InputNoteNoteBook inputNoteNoteBook =
                new InputNoteNoteBook(view, sc, model);
        inputNoteNoteBook.inputNote();
    }
}
