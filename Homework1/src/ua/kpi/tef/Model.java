package ua.kpi.tef;

/**
 * Created by User on 17.03.2016.
 */
public class Model {
    private int intValue;
    private String stringValue;

    // The Program logic

    /**
     * in this method add valueInt with this.value
     * @param valueInt
     * @return sum
     */
    public int addIntOurValue(int valueInt){
        return intValue += valueInt;
    }

    public String addStringOurValue(String valueString) {
        this.stringValue = this.stringValue.concat(valueString);
        return stringValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void setIntValue(int valueInt) {
        this.intValue = valueInt;
    }

    public void setStringValue(String valueString) {
        this.stringValue = valueString;
    }
}
