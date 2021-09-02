/*
 * Java Autumn 2021 - 04. String processing + Wrapper classes - NoteBook
 *
 * View
 *
 */
package ua.training.view;

import java.util.Locale;
import java.util.ResourceBundle;

import static ua.training.view.TextConstant.*;

/**
 *
 */
public class View {

    // App Settings Resource Bundle
    public static final ResourceBundle app_bundle =
            ResourceBundle.getBundle(
                    APP_SETTINGS_BUNDLE_NAME,
                    new Locale("en"));

    // Messages Resource Bundle
    static String MESSAGES_BUNDLE_NAME = "messages";
    public static final ResourceBundle bundle =
            ResourceBundle.getBundle(
                    MESSAGES_BUNDLE_NAME,
                    new Locale(
                            app_bundle.getString(APP_SETTINGS_LANGUAGE),
                            app_bundle.getString(APP_SETTINGS_COUNTRY)));

    /**
     *
     * @param message
     */
    public void printMessage(String message){
        System.out.println(message);
    }
    /**
     *
     * @param message
     * @return
     */
    public String concatenationString(String... message){
            StringBuilder concatString = new StringBuilder();
            for(String v : message) {
                concatString = concatString.append(v);
            }
            return new String(concatString);
    }

    public void printStringInput(String message) {
        printMessage(concatenationString(
                bundle.getString(INPUT_STRING_DATA),
                bundle.getString(message)));
    }

    public void printWrongStringInput(String message) {
        printMessage(concatenationString(
                bundle.getString(WRONG_INPUT_DATA),
                bundle.getString(INPUT_STRING_DATA),
                bundle.getString(message)));
    }


}
