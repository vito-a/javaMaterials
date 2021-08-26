//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.itstep;

public class View {
    public static final String INPUT_INT_DATA = "Input INT value = ";
    public static final String INPUT_DOUBLE_DATA = "Input DOUBLE value = ";
    public static final String INPUT_STRING_DATA = "Input STRING value = ";
    public static final String WRONG_INPUT_DATA = "Wrong input! Repeat please! ";
    public static final String OUR_INT = "INT value = ";
    public static final String OUR_DOUBLE = "DOUBLE value = ";
    public static final String OUR_STRING = "STRING value = ";
    public static final String SMALLER = "Your number is lower than a random one, please retry: ";
    public static final String BIGGER = "Your number is bigger than a random one, please retry: ";
    public static final String SUCCESS = "You have correctly guessed the number: ";

    public View() {
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
