package ua.training.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.training.repository.Color;

@Component
@Qualifier("redBean")
@Getter
@Setter
public
class Red implements Color {
    String color = "Red";

    @Override
    public void design () {
        System.out.println(color);
    }
}