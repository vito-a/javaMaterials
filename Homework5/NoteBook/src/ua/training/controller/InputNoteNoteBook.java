/*
 * Java Autumn 2021 - 08. Exceptions  Basic IO - NoteBook
 *
 * Controller
 *
 */
package ua.training.controller;

import ua.training.view.View;
import ua.training.model.Model;

import java.util.Scanner;

import static ua.training.view.TextConstant.*;
import static ua.training.controller.RegexContainer.*;

/**
 *
 */
public class InputNoteNoteBook {
    private View view;
    private Model model;
    private Scanner sc;

    private String lastName;
    private String firstName;
    private String familyName;
    private String fullName;
    private String login;
    private String nickName;
    private String comment;
    public userGroup group;
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

    public InputNoteNoteBook(View view, Scanner sc, Model model) {
        this.view = view;
        this.sc = sc;
        this.model = model;
    }

    public void inputNote() {
        UtilityController utilityController =
                new UtilityController(sc, view);

        String country = view.app_bundle.getString(APP_SETTINGS_COUNTRY);

        String str = (String.valueOf(View.bundle.getLocale()).equals("ua")) ? REGEX_NAME_UA : REGEX_NAME_EN;

        this.lastName = utilityController.inputStringValueWithScanner(LAST_NAME, str);
        this.firstName = utilityController.inputStringValueWithScanner(FIRST_NAME, str);
        this.familyName = utilityController.inputStringValueWithScanner(FAMILY_NAME, str);
        this.fullName = this.lastName + " " + this.firstName.substring(0, 1) + ".";
        this.login = utilityController.inputStringValueWithScanner(LOGIN_DATA, REGEX_LOGIN);
        this.nickName = utilityController.inputStringValueWithScanner(NICKNAME_DATA, REGEX_NICKNAME);
        this.comment = utilityController.inputTextValueWithScanner(COMMENT_DATA, REGEX_COMMENT);
        this.group = utilityController.inputUserGroupValueWithScanner(GROUP_DATA, REGEX_GROUP);
        this.homePhone = utilityController.inputStringValueWithScanner(HOME_PHONE_DATA, REGEX_PHONE);
        this.mobilePhone = utilityController.inputStringValueWithScanner(MOBILE_PHONE_DATA, REGEX_PHONE);
        this.mobilePhone2 = utilityController.inputStringValueWithScanner(MOBILE_PHONE2_DATA, REGEX_PHONE);
        this.email = utilityController.inputStringValueWithScanner(EMAIL_DATA, REGEX_EMAIL);
        this.skypeAddress = utilityController.inputStringValueWithScanner(SKYPE_DATA, REGEX_SKYPE);
        this.postCode = utilityController.inputStringValueWithScanner(POSTCODE_DATA, REGEX_POSTCODE);
        str = (String.valueOf(View.bundle.getLocale()).equals("ua")) ? REGEX_CITY_UA : REGEX_CITY_EN;
        this.city = utilityController.inputTextValueWithScanner(CITY_DATA, str);
        str = (String.valueOf(View.bundle.getLocale()).equals("ua")) ? REGEX_STREET_UA : REGEX_STREET_EN;
        this.streetName = utilityController.inputTextValueWithScanner(STREET_DATA, str);
        this.houseNumber = utilityController.inputStringValueWithScanner(HOUSE_DATA, REGEX_HOUSE);
        this.apartmentNumber = utilityController.inputStringValueWithScanner(APARTMENT_DATA, REGEX_APARTMENT);
        this.fullAddress = this.postCode + " " + this.city + " " + this.streetName + " " + this.houseNumber + " " + this.apartmentNumber;
        this.createdDate = utilityController.inputStringValueWithScanner(CREATED_DATA, REGEX_DATE);
        this.updatedDate = utilityController.inputStringValueWithScanner(UPDATED_DATA, REGEX_DATE);

        model.setFieldValue("lastName",        this.lastName);
        model.setFieldValue("firstName",       this.firstName);
        model.setFieldValue("familyName",      this.familyName);
        model.setFieldValue("fullName",        this.fullName);
        model.setFieldValue("login",           this.login);
        model.setFieldValue("nickName",        this.nickName);
        model.setFieldValue("comment",         this.comment);
        model.setFieldValue("homePhone",       this.homePhone);
        model.setFieldValue("mobilePhone",     this.mobilePhone);
        model.setFieldValue("mobilePhone2",    this.mobilePhone2);
        model.setFieldValue("email",           this.email);
        model.setFieldValue("skypeAddress",    this.skypeAddress);
        model.setFieldValue("postCode",        this.postCode);
        model.setFieldValue("city",            this.city);
        model.setFieldValue("streetName",      this.streetName);
        model.setFieldValue("houseNumber",     this.houseNumber);
        model.setFieldValue("apartmentNumber", this.apartmentNumber);
        model.setFieldValue("fullAddress",     this.fullAddress);
        model.setFieldValue("createdDate",     this.createdDate);
        model.setFieldValue("updatedDate",     this.updatedDate);
        model.setGroup(this.group);
    }

    public void inputLogin(){
        UtilityController utilityController =
                new UtilityController(sc, view);
        this.login =
                utilityController.inputStringValueWithScanner
                        (LOGIN_DATA, REGEX_LOGIN);
        model.setFieldValue("login", this.login);
    }
}
