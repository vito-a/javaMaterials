package ua.training;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
       Employee employee1 = new Employee("Podoprygora",
               "Taras",
               "Oleksijovich",
               "podoprygora.taras2021@gmail.com",
               new Date("06/03/2021"), 1000);

       Employee employee2 = new Employee();
       employee2.setLastName("Omelchenko");
       employee2.setFirstName("Petro");
       employee2.setFamilyName("Oleksandrovych");
       employee2.setEmail("omelchenko.petro2021@gmail.com");
       employee2.setCreatedDate(new Date("17/07/2021"));
       employee2.setSalary(2000);

	   Department department1 = new Department();
	   department1.addEmployee(employee1);
	   department1.addEmployee(employee2);

	   Payroll payroll1 = new Payroll(3500);
	   payroll1.setPayrollType(PayrollTypes.EQUAL);
	   department1.setPayroll(payroll1);

	   System.out.println("Salary for " + employee1.getFullName() + " is: " + payroll1.getSalary(department1, employee1));
    }
}
