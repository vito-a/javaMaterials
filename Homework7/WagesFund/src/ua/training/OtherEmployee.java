package ua.training;

import java.util.Date;

public class OtherEmployee extends Employee {
    String position;

    public OtherEmployee(String lastName, String firstName, String familyName, String email, Date createdDate) {
        super(lastName, firstName, familyName, email, createdDate);
    }
}
