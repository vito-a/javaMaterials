package ua.training;

import java.util.Date;

public class OtherEmployee extends Employee {
    String position;

    public OtherEmployee(String lastName, String firstName, String familyName, String email, Date createdDate, int salary) {
        super(lastName, firstName, familyName, email, createdDate, salary);
    }
}
