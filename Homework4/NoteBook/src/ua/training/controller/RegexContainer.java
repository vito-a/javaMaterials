/*
 * Java Autumn 2021 - 04. String processing + Wrapper classes - NoteBook
 *
 * RegexContainer
 *
 */
package ua.training.controller;

/**
 *
 */
public interface RegexContainer {
    // Ukrainian name
    String REGEX_NAME_UA = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";
    // English name
    String REGEX_NAME_EN = "^[A-Z][a-z]{1,20}$";
    // Nickname
    String REGEX_NICKNAME = "^[A-Za-z0-9_-]{8,20}$";
    // User group
    enum UserGroup {
        VIP,
        REGULAR,
        EMPLOYEE
    }
    String REGEX_COMMENT = "[A-Z][a-z]{1,100}";
    String REGEX_GROUP = "(VIP)|(REGULAR)|(EMPLOYEE)";
    String REGEX_PHONE = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    String REGEX_EMAIL = "/[^\\s]*@[a-z0-9.-]*/i";
    String REGEX_SKYPE = "[a-zA-Z][a-zA-Z0-9\\.,\\-_]{5,31}";
    String REGEX_POSTCODE = "([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})";
    String REGEX_CITY = "^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$";
    String REGEX_STREET = "^([\\w\\s\\W]+[\\w\\W]?)$";
    String REGEX_HOUSE = "^[0-9]{0,7}$";
    String REGEX_APARTMENT = "^[0-9]{0,7}$";
    String REGEX_DATE = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
}
