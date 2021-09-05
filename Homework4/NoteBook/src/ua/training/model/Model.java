/*
 * Java Autumn 2021 - 04. String processing + Wrapper classes - NoteBook
 *
 * Model
 *
 */
package ua.training.model;

import java.util.Map;
import java.util.HashMap;

import static ua.training.controller.RegexContainer.*;

/**
 *
 */
public class Model {
    private Map<String, String> userFields = new HashMap<String, String>();
    private userGroup group;

    public String getFieldValue(String fieldName) {
        return userFields.get(fieldName);
    }
    public void setFieldValue(String fieldName, String fieldValue) {
        userFields.put(fieldName, fieldValue);
    }
    public userGroup getGroup() {
        return this.group;
    }
    public void setGroup(userGroup groupValue) {
        this.group = groupValue;
    }
}
