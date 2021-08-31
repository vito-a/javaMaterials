/*
 * Java Autumn 2021 - 02. Arrays + Intro in Testing - homework2 - the Number Guessing game
 *
 * Controller
 *
 */
package ua.training;

import java.util.Scanner;
import java.util.Random;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        StringBuilder var2 = new StringBuilder();
        this.model.setRandomIntValue(rand.nextInt(this.model.MAX_RANDOM_VALUE + 1));
        while (this.model.getIntValue() != this.model.getRandomIntValue()) {
            this.model.setIntValue(this.inputIntValueWithScanner(sc));
            this.view.printMessage(var2.append("INT value = ").append(this.model.getIntValue()).append("\r\n").toString());
            if (this.model.getIntValue() > this.model.getRandomIntValue()) {
                view.printMessage(view.BIGGER + this.model.getIntValue());
            } else if (this.model.getIntValue() < this.model.getRandomIntValue()) {
                view.printMessage(view.SMALLER + this.model.getIntValue());
            }
        }
        view.printMessage(View.SUCCESS + this.model.getRandomIntValue());
    }

    private String inputStringValueWithScanner(Scanner sc) {
        this.view.printMessage("Input STRING value = ");
        return sc.next();
    }

    private double inputDoubleValueWithScanner(Scanner sc) {
        this.view.printMessage("Input DOUBLE value = ");

        while(!sc.hasNextDouble()) {
            StringBuilder var2 = new StringBuilder();
            var2.append("Wrong input! Repeat please! ");
            this.view.printMessage(var2.append("Input DOUBLE value = ").toString());
            sc.next();
        }

        return sc.nextDouble();
    }

    public int inputIntValueWithScanner(Scanner sc) {
        this.view.printMessage("Input INT value = ");

        while(!sc.hasNextInt()) {
            StringBuilder var2 = new StringBuilder();
            var2.append("Wrong input! Repeat please! ");
            this.view.printMessage(var2.append("Input INT value = ").toString());
            sc.next();
        }

        return sc.nextInt();
    }
}
