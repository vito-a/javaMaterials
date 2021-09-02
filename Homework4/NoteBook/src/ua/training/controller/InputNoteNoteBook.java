/*
 * Java Autumn 2021 - 04. String processing + Wrapper classes - NoteBook
 *
 * Controller
 *
 */
package ua.training.controller;

import ua.training.view.View;

import java.util.Scanner;

import static ua.training.view.TextConstant.*;

/**
 *
 */
public class InputNoteNoteBook implements RegexContainer {
    private View view;
    private Scanner sc;

    private String lastName;
    private String firstName;
    private String familyName;
    private String fullName;
    private String nickName;
    private String comment;
    private UserGroup group;
    private String homePhone;
    private String mobilePhone;
    private String mobilePhone2;
    private String email;
    private String skypeAddress;
    private String postCode;
    private String city;
    private String streetName;
    private String houseNumber;
    private String apartmentNumber;
    private String fullAddress;
    private String createdDate;
    private String updatedDate;

    public InputNoteNoteBook(View view, Scanner sc) {
        this.view = view;
        this.sc = sc;
    }

    public void inputNote() throws NoSuchFieldException {
        UtilityController utilityController = new UtilityController(sc, view);

        String country = View.app_bundle.getString(APP_SETTINGS_COUNTRY);
        String str = this.getRegex("REGEX_NAME_" + country);

        this.lastName = utilityController.inputStringValueWithScanner(LAST_NAME, str);
        this.firstName = utilityController.inputStringValueWithScanner(FIRST_NAME, str);
        this.familyName = utilityController.inputStringValueWithScanner(FAMILY_NAME, str);
        this.fullName = this.lastName + " " + this.firstName.substring(0, 1) + ".";
        this.nickName = utilityController.inputStringValueWithScanner(NICKNAME_DATA, REGEX_NICKNAME);
        this.comment = utilityController.inputStringValueWithScanner(COMMENT_DATA, REGEX_COMMENT);
        this.group = (UserGroup) utilityController.inputEnumValueWithScanner(this.group, GROUP_DATA, REGEX_GROUP);
        this.homePhone = utilityController.inputStringValueWithScanner(HOME_PHONE_DATA, REGEX_PHONE);
        this.mobilePhone = utilityController.inputStringValueWithScanner(MOBILE_PHONE_DATA, REGEX_PHONE);
        this.mobilePhone2 = utilityController.inputStringValueWithScanner(MOBILE_PHONE2_DATA, REGEX_PHONE);
        this.email = utilityController.inputStringValueWithScanner(EMAIL_DATA, REGEX_EMAIL);
        this.skypeAddress = utilityController.inputStringValueWithScanner(SKYPE_DATA, REGEX_SKYPE);
        this.postCode = utilityController.inputStringValueWithScanner(POSTCODE_DATA, REGEX_POSTCODE);
        this.city = utilityController.inputStringValueWithScanner(CITY_DATA, REGEX_CITY);
        this.streetName = utilityController.inputStringValueWithScanner(STREET_DATA, REGEX_STREET);
        this.houseNumber = utilityController.inputStringValueWithScanner(HOUSE_DATA, REGEX_HOUSE);
        this.apartmentNumber = utilityController.inputStringValueWithScanner(APARTMENT_DATA, REGEX_APARTMENT);
        this.fullAddress = this.postCode + " " + this.city + " " + this.streetName + " " + this.houseNumber + " " + this.apartmentNumber;
        this.createdDate = utilityController.inputStringValueWithScanner(CREATED_DATA, REGEX_DATE);
        this.updatedDate = utilityController.inputStringValueWithScanner(UPDATED_DATA, REGEX_DATE);
    }
}
