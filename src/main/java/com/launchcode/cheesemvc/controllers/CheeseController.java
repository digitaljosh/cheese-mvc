package com.launchcode.cheesemvc.controllers;


import com.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import com.launchcode.cheesemvc.models.Cheese;

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
        return "cheese/add";
    }

    // this handler processes the form
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese) {
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
        /**
        model.addAttribute("cheeseName", cheese.getName());
        model.addAttribute("cheeseDescription", cheese.getDescription());
        model.addAttribute("cheeseId", cheese.getCheeseId());
        **/
        model.addAttribute("cheese", cheese);
        //return appropriate template string
        return "cheese/edit";
    }


    @RequestMapping(value="edit")
    public String processEditForm(@RequestParam String name,
                                  @RequestParam int cheeseId,
                                  @RequestParam String description) {

        // Query CheeseData for the cheese with the given id
        Cheese cheese = CheeseData.getById(cheeseId);

        // then update its name and description.
        // CheeseData.add(cheese);
        cheese.setName(name);
        cheese.setDescription(description);

        // Redirect the user to the home page.
        return "redirect:";
    }

}
