/*
 * Java Autumn 2021 - 08. Exceptions  Basic IO - NoteBook
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
    public boolean validateFields()
            throws LoginExistsException {
        String loginName;
        NoteBookDAO dao = new NoteBookDAO();
        loginName = this.getFieldValue("login");

        if (dao.checkLogin(loginName)) {
            throw new LoginExistsException("Login exists", loginName);
        } else {
            return true;
        }
    }

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
