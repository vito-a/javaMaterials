package ua.training.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.training.model.Address;
import ua.training.model.Company;

@Component
@Getter
@Setter
public class Employee {
    @Autowired
    Company company;

    public Employee(Company company) {
        this.company = company;
    }

    public Employee() {

    }
}
