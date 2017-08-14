package com.launchcode.cheesemvc.controllers;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by Josh Markus
 */

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    static ArrayList<String> cheesesToDeleteList = new ArrayList<>();

    // request path will be /cheese due to @RequestMapping("cheese") above
    @RequestMapping(value = "")  // path /cheese
    public String index(Model model) {

        // if there are no items to delete
        if (cheesesToDeleteList.size() < 1) {
            model.addAttribute("cheeses", cheeses);
            model.addAttribute("title", "My Cheeses");

            // display list of cheeses
            return "cheese/index";

        // if there are items to be deleted:
        } else {
            for (String item : cheesesToDeleteList) {
                cheeses.remove(item);
            }
        }
            cheesesToDeleteList.clear(); // clears deletedCheeseList so it doesn't affect subsequent deletions
            model.addAttribute("cheeses", cheeses);
            model.addAttribute("title", "My Cheeses");

            // display amended list after deleting items
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
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription, Model model) {

        if (cheeseName.isEmpty()) {
            model.addAttribute("title", "Add Cheese - 'Name' error!");
            return "cheese/add";
        }
        cheeses.put(cheeseName, cheeseDescription);

        // redirect to /cheese (leaving it empty after ":" directs to parent path)
        return "redirect:";
    }

    // this handler displays 'delete cheese' form
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteCheeseForm(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Delete Cheese");

        return "cheese/delete";
    }

    // this handler processes delete cheese
    @RequestMapping(value="delete", method = RequestMethod.POST)
    public String processDeleteCheeseForm(@RequestParam ArrayList<String> deletedCheeses) {

        for (String item : deletedCheeses)
            cheesesToDeleteList.add(item);

        return "redirect:";
    }
}
