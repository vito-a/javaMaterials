package ua.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.training.model.Address;
import ua.training.model.Company;
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
@ComponentScan(basePackageClasses = Company.class)
@ComponentScan(basePackageClasses = Red.class)
// @EnableSpringConfigured
public class Config {
    @Bean
    public Address getAddress() {
        return new Address("High Street", 1000);
    }

    @Bean
    // @Qualifier("redBean")
    public Color redBean() { return new Red(); }
}