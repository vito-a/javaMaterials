package ua.training;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
       Employee employee1 = new Employee("Podoprygora",
               "Taras",
               "Oleksijovich",
               "podoprygora.taras2021@gmail.com",
               new Date("01/01/2021"));

       Employee employee2 = new Employee();
       employee2.setLastName("Omelchenko");
       employee2.setFirstName("Petro");
       employee2.setFamilyName("Oleksandrovych");
       employee2.setEmail("omelchenko.petro2021@gmail.com");
       employee2.setCreatedDate(new Date("01/01/2021"));

	   Department department1 = new Department();
	   department1.addEmployee(employee1);
	   department1.addEmployee(employee2);
    }
}
