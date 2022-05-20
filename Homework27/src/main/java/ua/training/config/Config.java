package ua.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.training.model.entity.Address;
import ua.training.model.entity.Company;
import ua.training.model.entity.Employee;
import ua.training.model.entity.Red;
import ua.training.repository.Color;

/**
 * A configuration class supplying bean metadata to an IoC container.
 *
 * The configuration class produces a bean of type Address. It also carries the @ComponentScan
 * annotation, which instructs the container to look for beans in the package containing the Company class.
 *
 * When a Spring IoC container constructs objects of those types, all the objects are called Spring beans,
 * as they are managed by the IoC container.
 */
@Configuration
@ComponentScan("ua.training")
//@ComponentScan(basePackageClasses = Company.class)
//@ComponentScan(basePackageClasses = Address.class)
//@ComponentScan(basePackageClasses = Employee.class)
//@ComponentScan(basePackageClasses = Red.class)
// @EnableSpringConfigured
public class Config {
//    @Value("High Street")
//    public String street;

//    @Value("1000")
//    public int number;

    @Bean
    public Address getAddress() {
        return new Address();
    }

    @Bean
    public Company getCompany() {
        return new Company(this.getAddress());
    }

    @Bean
    public Employee createEmployee() {
        return new Employee(this.getCompany());
    }

    @Bean
    // @Qualifier("redBean")
    public Color redBean() { return new Red(); }
}