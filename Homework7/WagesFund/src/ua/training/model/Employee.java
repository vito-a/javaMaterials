package ua.training.model;

import java.util.Date;

public class Employee {
    private String lastName;
    private String firstName;
    private String familyName;
    private String email;
    private Date createdDate;
    private Date birthday;
    private int salary;

    public Employee() {
    }

    public Employee(String lastName, String firstName, String familyName, String email, Date createdDate, int salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;
        this.createdDate = createdDate;
        this.salary = salary;
    }

    public String getFullName() {
        return this.lastName + " " + this.firstName + " " + this.familyName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    int getSalary() {
        return this.salary;
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

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
