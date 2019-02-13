package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * Created by LaunchCode
 */
@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private org.launchcode.models.Category category;


    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;


    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() {}


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public org.launchcode.models.Category getCategory() {
        return category;
    }

    public void setCategory (org.launchcode.models.Category category) {
        this.category = category;
    }


    public List<Menu> getMenus() {
        return menus;
    }
}


    /**@Override
    public boolean equals(Object a) {
        if (this == a) return true;
        if (!(a instanceof Cheese)) return false;
        Cheese cheese = (Cheese) a;
        return getName().equals(cheese.getName())  &&
                getDescription().equals(cheese.getDescription()) &&
                getType() ==cheese.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getType());
    }**/

