package ua.training.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.model.entity.Address;

@Component
@Getter
@Setter
public class Company {
    @Autowired
    private Address address;

    public Company() {}

    public Company(Address address) {
        this.address = address;
    }

    // getter, setter and other properties
}