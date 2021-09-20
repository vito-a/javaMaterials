package ua.training.model;

import ua.training.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Employee {
    private View view;

    private String lastName;
    private String firstName;
    private String familyName;
    private String email;
    private Date createdDate;
    private Date birthday;
    private EmployeeTypes type;
    private long bonus;
    private long baseSalary;

    public Employee() {
    }

    public Employee(String lastName, String firstName, String familyName, String email,
                    String createdDate, String birthday, long baseSalary, long bonus, EmployeeTypes type) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(AppSettings.appDatesFormat);

        this.lastName = lastName;
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;

        try {
            this.createdDate = dateFormat.parse(createdDate);
        } catch (ParseException e) {
            view.printDateParsingError("2021-07-17");
        }

        try {
            this.birthday = dateFormat.parse(birthday);
        } catch (ParseException e) {
            view.printDateParsingError("1990-09-06");
        }

        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.type = type;
    }

    public String getFullName() {
        return this.lastName + " " + this.firstName + " " + this.familyName;
    }

    public void setBaseSalary(long baseSalary) {
        this.baseSalary = baseSalary;
    }

    public long getBaseSalary() {
        return this.baseSalary;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedDate(String createdDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(AppSettings.appDatesFormat);
        this.createdDate = dateFormat.parse(createdDate);
    }

    public void setBirthday(String birthday) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(AppSettings.appDatesFormat);
        this.birthday = dateFormat.parse(birthday);
    }

    public Date getBirthday() { return this.birthday; }

    public void setType(EmployeeTypes type) { this.type = type; }

    public EmployeeTypes getType() { return this.type; }

    public void setBonus(long bonus) { this.bonus = bonus; }

    public long getBonus() { return this.bonus; }

    public boolean hasBonus() {
        Date currentDate = new Date();
        LocalDate localDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int currentMonth = localDate.getMonthValue();

        LocalDate birthdayDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int birthdayMonth = birthdayDate.getMonthValue();

        return currentMonth == birthdayMonth;
    }
}
