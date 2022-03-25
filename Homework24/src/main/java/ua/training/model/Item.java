/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Question: Here is an entity implementation and a UT that tests its functionality; Without changing the UT code, make this module build and UT cases pass properly.
 * The UT cases tests for equality. First it checks that a similar, NOT SAME, Item should be asserted as equal because their field contents are the same.
 * The other case is testing its uniqueness in a collection, specifically in a HashSet... such that two Items that have the same values are treated
 * as equal so in a hashset only the first one inserted should exist.
 *
 */
@Entity
public class Item implements Serializable {
    
    @Id
    private Long id;
    
    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TASK AREA START

    // hashcode and equals are necessary
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        // return o instanceof Item && ((Item) o).getId().equals(this.getId()) && ((Item) o).getName().equals(this.getName());
        Item that = (Item) o;
        return Objects.equals(this.getId(), that.getId()) && Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName());
    }

    // TASK AREA END
}
