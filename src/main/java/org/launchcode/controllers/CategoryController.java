package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("category")
public class CategoryController {


    @Autowired
    private CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll()); //cheeseDao or cheeseData?
        model.addAttribute("title", "Category");

        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCategoryForm(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());
        model.addAttribute("categories", categoryDao.findAll());
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCategoryForm(@ModelAttribute @Valid Category newCategory, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            model.addAttribute("categories" , categoryDao.findAll());
            return "category/add";
        }

        categoryDao.save(newCategory);
        return "redirect:";

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
