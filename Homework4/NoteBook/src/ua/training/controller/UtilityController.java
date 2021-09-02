/*
 * Java Autumn 2021 - 04. String processing + Wrapper classes - NoteBook
 *
 * UtilityController
 *
 */
package ua.training.controller;

import  ua.training.view.View;

import java.util.Scanner;

import static ua.training.controller.RegexContainer.*;

/**
 *
 */
public class UtilityController {
    private Scanner scanner;
    private View view;

    public UtilityController(Scanner scanner, View view) {
        this.scanner = scanner;
        this.view = view;
    }

    String inputStringValueWithScanner(String message, String regex) {
        String res;
        view.printStringInput(message);
        while( !(scanner.hasNext() && (res = scanner.next()).matches(regex))) {
            view.printWrongStringInput(message);
        }
        return res;
    }

    Enum inputEnumValueWithScanner(Enum enumType, String message, String regex) {
        String res;
        view.printStringInput(message);
        while( !(scanner.hasNext() && (res = scanner.next()).matches(regex))) {
            view.printWrongStringInput(message);
        }
        return Enum.valueOf(enumType.getClass(),res);
    }
}
