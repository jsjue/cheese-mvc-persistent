package org.launchcode.models.form;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {
    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;
    private Iterable<Cheese> cheeses;
    private Menu menu;
    public AddMenuItemForm (Iterable<Cheese> cheeses, Menu menu) {
        this.cheeses = cheeses;
        this.menu = menu;

    }

    public int getMenuId() {
        return menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }
    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;


    }

    public Menu getMenu() {
        return menu;
    }
}
