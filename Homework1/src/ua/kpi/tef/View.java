package ua.kpi.tef;

/**
 * Created by User on 17.03.2016.
 */
public class View {
    // Text's constants
    public static final String INPUT_INT_DATA = "Please input 'Hello' String value = ";
    public static final String WRONG_INPUT_INT_DATA = "Wrong input! Repeat please! ";
    public static final String INPUT_STRING_HELLO_DATA = "Please input \"Hello\" String value = ";
    public static final String INPUT_STRING_WORLD_DATA = "Please input \"world!\" String value = ";
    public static final String WRONG_INPUT_STRING_DATA = "Wrong input! Repeat please! ";
    public static final String OUR_INT = "INT value = ";
    public static final String OUR_STRING = "String value = ";

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printMessageAndInt(String message, int value){
        System.out.println(message + value);
    }

    public void printMessageAndString(String message, String value){
        System.out.println(message + value);
    }
}
