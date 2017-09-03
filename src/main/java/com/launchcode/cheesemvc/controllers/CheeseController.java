package com.launchcode.cheesemvc.controllers;


import com.launchcode.cheesemvc.models.CheeseData;
import com.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



import com.launchcode.cheesemvc.models.Cheese;

import javax.validation.Valid;

/**
 * Created by Josh Markus
 */

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {


    // request path will be /cheese due to @RequestMapping("cheese") above
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        // display list of cheeses
        return "cheese/index";
    }


    // this handler displays the form
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        // passes skeleton cheese object to form
        model.addAttribute(new Cheese());
        // returns array of cheese types
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    // this handler processes the form
    // @Valid specifies that cheese object properties
    // follow the validation settings in Model (cheese.java)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }

        // model binding
        CheeseData.add(newCheese);
        return "redirect:";
    }


    // this handler displays 'delete cheese' form
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Delete Cheese");
        return "cheese/delete";
    }

    // this handler processes delete cheese
    @RequestMapping(value="delete", method = RequestMethod.POST)
    public String processDeleteCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese cheese = CheeseData.getById(cheeseId);
        // put it in model
        model.addAttribute("cheese", cheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
        //return appropriate template string
        return "cheese/edit";
    }


    @RequestMapping(value="edit")
    public String processEditForm(@RequestParam int cheeseId,
                                  @RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam CheeseType type,
                                  @ModelAttribute @Valid Cheese cheese,
                                  Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("cheese", cheese);
            model.addAttribute("cheeseTypes", CheeseType.values());
            //return appropriate template string
            return "cheese/edit";
        }

        // Query CheeseData for the cheese with the given id
        Cheese cheeseToEdit = CheeseData.getById(cheeseId);

        // then update its name and description.
        // CheeseData.add(cheese);
        cheeseToEdit.setName(name);
        cheeseToEdit.setDescription(description);
        cheeseToEdit.setType(type);

        // Redirect the user to the home page.
        return "redirect:";
    }

}
