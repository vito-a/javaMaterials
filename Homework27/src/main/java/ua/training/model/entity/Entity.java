package ua.training.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.training.repository.Color;

import javax.annotation.Resource;

@Component
@Getter
@Setter
public class Entity {
    private Color color;

    @Autowired
    public Entity(@Qualifier("red") Color color) {
        this.color = color;
    }

    public void draw() {
        // color.design();
        System.out.println(color.getColor());
    }
}
