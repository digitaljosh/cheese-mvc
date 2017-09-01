package com.launchcode.cheesemvc.controllers;

import com.launchcode.cheesemvc.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value = "user")
public class UserController {


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Sign up!");
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute("user") User user, @RequestParam String verify) {
        // creates user object

        // check that verify matches password in user object
        // if it does, render index.html with a username welcome message
        if (verify.matches(user.getPassword())) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("title", "You're in!");
            return "user/index";

        // if it doesn't match, render form again with fields populated
        // and message indicating what went wrong
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "Passwords don't match");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("error", error);
            model.addAttribute("title", "Sign up!");

            return "user/add";
        }




    }
}
