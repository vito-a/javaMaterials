package ua.training.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Employee {
    @Autowired
    Company company;

    public Employee() {}

    public Employee(Company company) {
        this.company = company;
    }
}
