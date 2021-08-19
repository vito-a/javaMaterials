package ua.kpi.tef;

import java.util.Scanner;

/**
 * Created by User on 17.03.2016.
 */
public class Controller {
    // The Constants
    public static final double PI = 3.14;
    public static final int FOUR = 4;

    // The Regex
    // «nick@mail.com»
    public static final String REGEX_MAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    // «http://www.my-site.com»
    public static final String REGEX_URL = "^((https?|ftp)\\:\\/\\/)?([a-z0-9]{1})((\\.[a-z0-9-])|([a-z0-9-]))*\\.([a-z]{2,6})(\\/?)$";
    // «+38(044)555-55-55»
    public static final String REGEX_PHONE = "^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";

    // Constructor
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model  = model;
        this.view = view;
    }

    // The Work method
    public void processUser(){
        Scanner sc = new Scanner(System.in);

        model.setStringValue(inputStringValueWithScanner(sc));

        view.printMessageAndString(View.OUR_STRING, model.getStringValue());
    }

    // The Utility methods
    public String inputStringValueWithScanner(Scanner sc) {
        String inputStringValue = "";
        String finalStringValue = "";

        view.printMessage(View.INPUT_STRING_HELLO_DATA);
        while( ! sc.hasNext("Hello")) {
            view.printMessage(View.WRONG_INPUT_STRING_DATA + View.INPUT_STRING_HELLO_DATA);
            inputStringValue = sc.next();
        }
        view.printMessage(View.INPUT_STRING_WORLD_DATA);
        inputStringValue = sc.next();
        finalStringValue = finalStringValue.concat(inputStringValue);
        while( ! sc.hasNext("world!")) {
            view.printMessage(View.WRONG_INPUT_STRING_DATA + View.INPUT_STRING_WORLD_DATA);
            inputStringValue = sc.next();
        }
        inputStringValue = sc.next();
        finalStringValue = finalStringValue.concat(" " + inputStringValue);

        return finalStringValue;
    }
}
