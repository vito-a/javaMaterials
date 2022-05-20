package ua.training;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.training.config.Config;
import ua.training.model.entity.Address;
import ua.training.model.entity.Company;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Entity;
import ua.training.repository.Color;

import java.util.Objects;

/**
 * Main application class
 *
 */
public class App {
    public static void main( String[] args ) {
        // Traditional Approach
        // Normally, we create objects with their classes' constructors:
        /*
          Address address = new Address("High Street", 1000);
          Company company = new Company(address);
        */

        // IoC in Action
        // Since we defined beans in a configuration class, we'll need an instance
        // of the AnnotationConfigApplicationContext class to build up a container:

        // XML context:
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Employee employee = context.getBean("employee", Employee.class);
        // Company company = employee.getCompany();
        // Address address = company.getAddress();

        // Annotation context:
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Employee employee = context.getBean("employee", Employee.class);
        Company company = employee.getCompany();
        Address address = company.getAddress();

        assert(Objects.equals(address.getStreet(), "High Street"));
        assert(Objects.equals(address.getNumber(), 1000));

        System.out.println(address.getStreet());
        System.out.println(address.getNumber());

        Entity entity = new Entity(context.getBean("redBean", Color.class));
        entity.draw();
    }
}
