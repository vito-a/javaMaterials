/*
 * Java Autumn 2021 - 08. Exceptions  Basic IO - NoteBook
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
    final public static String REGEX_NAME_UA = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,20}$";
    // English name
    final public static String REGEX_NAME_EN = "^[A-Z][a-z]{1,20}$";
    // Nickname
    String REGEX_NICKNAME = "^[A-Za-z0-9_-]{8,20}$";
    String REGEX_LOGIN = "^[A-Za-z0-9_-]{5,20}$";
    // User group
    enum userGroup {
        VIP,
        REGULAR,
        EMPLOYEE
    }
    String REGEX_COMMENT = "^[A-Za-z ]{1,100}$";
    String REGEX_GROUP = "(VIP|REGULAR|EMPLOYEE)";
    String REGEX_PHONE = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    String REGEX_SKYPE = "[a-zA-Z][a-zA-Z0-9\\.,\\-_]{5,31}";
    String REGEX_POSTCODE = "^[0-9]{5}$";
    String REGEX_CITY_EN = "^[A-Z][a-z]+(\\s[A-Z][a-z]+)?+$";
    String REGEX_STREET_EN = "^[A-Z][a-z]+(\\s[A-Z][a-z]+)?+$";
    String REGEX_CITY_UA = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']+(\\s[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']+)?+$";
    String REGEX_STREET_UA = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']+(\\s[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']+)?+$";
    String REGEX_HOUSE = "^[0-9]{0,7}$";
    String REGEX_APARTMENT = "^[0-9]{0,7}$";
    String REGEX_DATE = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
}
