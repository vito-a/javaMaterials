package ua.testing.periodicals;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

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

    @Value("${spring.datasource.driverClassName}")
    private String dbDriverClassName;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    /**
     * The entry point of application.
     *
     * @param args the application input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PeriodicalsApplication.class, args);
    }

}
