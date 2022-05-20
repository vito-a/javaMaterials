package ua.training.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.training.repository.Color;

@Component
@Qualifier("blueBean")
@Getter
@Setter
class Blue implements Color {
    String color = "Blue";

    @Override
    public void design () {
        System.out.println(color);
    }
}
