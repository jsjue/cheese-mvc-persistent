package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.form.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("menu")
public class MenuController {


    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("menus", menuDao.findAll()); //cheeseDao or cheeseData?
        model.addAttribute("title", "Menu");

        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCategoryForm(Model model) {
        model.addAttribute("title", "name");

        model.addAttribute("menu", new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCMenuForm(@ModelAttribute @Valid Menu newMenu, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            model.addAttribute("menu" , newMenu);
            return "category/add";
        }

        menuDao.save(newMenu);
        return "redirect:view/" + newMenu.getId();



    }

    @RequestMapping (value="view/{menuId}" , method = RequestMethod.GET)
    public String viewMenu (Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("cheeses" , menu.getCheeses());
        model.addAttribute("menuId" , menuId);
        model.addAttribute("title" , menu.getName());
        return "menu/view";
    }

    @RequestMapping (value="additem/{menuId}" , method = RequestMethod.GET)
    public String addItem (Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(cheeseDao.findAll() , menu);
        model.addAttribute("form" , form);
        model.addAttribute("title" , "Add Item To " + menu.getName());
        return "menu/add_menu_item";
    }

    @RequestMapping (value="addItem", method = RequestMethod.POST)
    public String addItem (Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Item to " + form.getMenu().getName());
            model.addAttribute("form", form);
            return "menu/add_menu_item";
        }


        Cheese cheese = cheeseDao.findOne(form.getCheeseId());
        Menu menu = menuDao.findOne(form.getMenuId());
        menu.addItem(cheese);
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }


    /**@RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", categoryDao.findAll());
        model.addAttribute("title", "Remove Category");
        return "category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }
    @RequestMapping (value = "edit/{cheeseId}" , method = RequestMethod.GET)
    public String displayCheeseEditForm(@PathVariable int cheeseId, Model model) {
        model.addAttribute("title" , "Edit Cheese" + cheeseDao.findOne(cheeseId).getName());
        model.addAttribute("cheese" , cheeseDao.findOne(cheeseId));
        model.addAttribute("categories" , cheeseDao.findAll());
        return "cheese/edit";

    }

    @RequestMapping(value = "edit/{cheeseId}" , method = RequestMethod.POST)
    public String processEditForm(@PathVariable int cheeseId, @RequestParam int categoryId, @ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("Title" , "Add Cheese");
            return "cheese/edit";

        }
        Cheese editedCheese = cheeseDao.findOne(cheeseId);
        editedCheese.setName(newCheese.getName());
        editedCheese.setDescription(newCheese.getDescription());
        editedCheese.setCategory(categoryDao.findOne(categoryId));
        cheeseDao.save(editedCheese);

        return "redirect : /cheese";

    }**/

}
