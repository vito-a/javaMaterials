package ua.training.model.entity;

import javax.persistence.*;

import static ua.training.model.constants.Constants.PERIODICAL_ID;
import static ua.training.model.constants.Constants.CATEGORY_ID;

/**
 * The Periodical entity.
 */
public class Periodical {
    private Long periodicalId;
    private String name;
    private String description;
    private String categoryId;
    private Long price;

    public Periodical() {
    }

    public Periodical(Long periodicalId, String name) {
        this.name = name;
        this.periodicalId = periodicalId;
    }

    public long getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(long periodicalId) {
        this.periodicalId = periodicalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
