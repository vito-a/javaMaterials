package ua.testing.periodicals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Periodicals application.
 *
 * Uses Spring Boot, Spring Security, Lombok, MySQL and Thymeleaf
 *
 * @see SpringApplication
 * @see SpringBootApplication
 *
 */
@SpringBootApplication
public class PeriodicalsApplication {

    /**
     * The entry point of application.
     *
     * @param args the application input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PeriodicalsApplication.class, args);
    }

}
