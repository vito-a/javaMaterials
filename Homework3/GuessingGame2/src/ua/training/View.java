package ua.training;

/**
 * Created by Student on 22.02.2017.
 */
public class View {
    // text constants
    public static final String EQUAL_SING = "=";
    public static final String SPACE_SING = " ";
    public static final String OPENS_SQUARE_BRACKET = "]";
    public static final String CLOSING_SQUARE_BRACKET = "[";

    public static final String INPUT_INT_DATA = "Input INT value ";
    public static final String WRONG_INPUT_INT_DATA = "Wrong input! Repeat please! ";
    public static final String CONGRATULATION = "CONGRATULATION! You WIN! Secret value = ";
    public static final String YOUR_WAY = "YOUR WAY = ";

    // Utilities methods
    public void printMessage (String message){
        System.out.println(message);
    }

    public String concatenationString (String... message){
        StringBuilder concatString = new StringBuilder();
        for (String v : message){
            concatString = concatString.append(v);
        }
        return new String(concatString);
    }
}
