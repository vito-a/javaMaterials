/*
 * Java Autumn 2021 - 02. Arrays + Intro in Testing - homework2 - the Number Guessing game
 *
 * Main
 *
 */

package ua.training;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.processUser();
    }
}
