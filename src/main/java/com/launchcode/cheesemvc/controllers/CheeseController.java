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

    static ArrayList<String> deletedCheeseList = new ArrayList<>();

    // request path will be /cheese due to @RequestMapping("cheese") above
    @RequestMapping(value = "")  // path: /cheese
    public String index(Model model) {

        if (deletedCheeseList.size() < 1) {
            model.addAttribute("cheeses", cheeses);
            model.addAttribute("title", "My Cheeses");

            return "cheese/index";

        // if there are items to be deleted:
        } else {
            /** Originally had 'cheeses.remove(item)' in the 'if statement'  but is was
             * throwing an error, I think because my cheese map was changing every iteration
             * so I had to find a way to keep a list of items to remove from cheese hashmap
             * without changing the hashmap while iterating
             */
            ArrayList<String> deleteList = new ArrayList<>();
            for (String entry : cheeses.keySet()) {
                if (deletedCheeseList.contains(entry)) {
                    deleteList.add(entry); // adds entry to be deleted to deleteList
                    }
                }
            /** iterates over each item in deleteList and
             * deletes it from cheese HashMap
              */
            for (String item : deleteList) {
                cheeses.remove(item);
            }
        }
            deletedCheeseList.clear(); // clears deletedCheeseList so it doesn't affect subsequent deletions
            model.addAttribute("cheeses", cheeses);
            model.addAttribute("title", "My Cheeses");

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
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {

        cheeses.put(cheeseName, cheeseDescription);

        // redirect to /cheese, leaving it empty after ":" directs to parent path
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
            deletedCheeseList.add(item);

        return "redirect:";
    }
}
