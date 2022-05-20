package ua.training.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Address {
//    private String street;
//    private int number;
    @Value("High Street")
    private String street;

    @Value("1000")
    private int number;

    public Address() {}

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }

    // getters and setters
}