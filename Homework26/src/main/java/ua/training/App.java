package ua.training;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.training.config.Config;
import ua.training.model.Company;

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

        // XML context:
        // ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // IoC in Action
        // Since we defined beans in a configuration class, we'll need an instance
        // of the AnnotationConfigApplicationContext class to build up a container:
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        //
        Company company = context.getBean("company", Company.class);

        assert(Objects.equals(company.getAddress().getStreet(), "High Street"));
        assert(Objects.equals(company.getAddress().getNumber(), 1000));

        System.out.println(company.getAddress().getStreet());
        System.out.println(company.getAddress().getNumber());
    }
}
