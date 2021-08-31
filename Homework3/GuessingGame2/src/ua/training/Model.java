/*
 * Java Autumn 2021 - 02. Arrays + Intro in Testing - homework2 - the Number Guessing game
 *
 * Model
 *
 */

package ua.training;

public class Model {
    public static final int MAX_RANDOM_VALUE = 100;

    private int intValue = -1;
    private int randomIntValue = -2;

    public Model() {
    }

    public int getIntValue() {
        return this.intValue;
    }

    public void setIntValue(int value) {
        this.intValue = value;
    }

    public int getRandomIntValue() {
        return this.randomIntValue;
    }

    public void setRandomIntValue(int value) {
        this.randomIntValue = value;
    }
}
