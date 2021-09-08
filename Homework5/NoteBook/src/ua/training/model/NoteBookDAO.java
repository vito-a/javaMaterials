/*
 * Java Autumn 2021 - 08. Exceptions  Basic IO - NoteBook
 *
 * NoteBookDAO
 *
 */
package ua.training.model;

import ua.training.controller.RegexContainer.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteBookDAO {

    private List<Map<String , String>> usersList  = new ArrayList<Map<String,String>>();

    NoteBookDAO() {
        String fullName;
        String fullAddress;
        Map<String, String> sampleFields = new HashMap<String, String>();

        sampleFields.put("lastName",        "Podoprygora");
        sampleFields.put("firstName",       "Taras");
        sampleFields.put("familyName",      "Oleksijovich");
        fullName = sampleFields.get("lastName") + " " + sampleFields.get("firstName").substring(0, 1) + ".";
        sampleFields.put("fullName",        fullName);
        sampleFields.put("login",           "taras");
        sampleFields.put("nickName",        "taras2021");
        sampleFields.put("comment",         "Test comment");
        sampleFields.put("homePhone",       "123456789");
        sampleFields.put("mobilePhone",     "123456789");
        sampleFields.put("mobilePhone2",    "123456789");
        sampleFields.put("email",           "taras2021@gmail.com");
        sampleFields.put("skypeAddress",    "taras2021");
        sampleFields.put("postCode",        "123456");
        sampleFields.put("city",            "Test city");
        sampleFields.put("streetName",      "Test street");
        sampleFields.put("houseNumber",     "123");
        sampleFields.put("apartmentNumber", "456");
        fullAddress = sampleFields.get("postCode")
                + " " + sampleFields.get("city")
                + " " + sampleFields.get("streetName")
                + " " + sampleFields.get("houseNumber")
                + " " + sampleFields.get("apartmentNumber");
        sampleFields.put("fullAddress",     fullAddress);
        sampleFields.put("createdDate",     "01/01/2021");
        sampleFields.put("updatedDate",     "01/01/2021");
        sampleFields.put("userGroup",       userGroup.REGULAR.toString());

        usersList.add(sampleFields);
    }

    public boolean checkLogin(String loginData){
        for (Map<String, String> map : usersList) {
            if (map.get("login").equals(loginData)) {
                return true;
            }
        }
        return  false;
    }
}
