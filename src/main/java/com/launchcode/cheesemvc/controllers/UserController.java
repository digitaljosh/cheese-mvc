package com.launchcode.cheesemvc.controllers;

import com.launchcode.cheesemvc.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping(value = "user")
public class UserController {


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUser(Model model) {
        // passes dummy user object to template
        model.addAttribute(new User());
        model.addAttribute("title", "Sign up!");
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUser(Model model, @ModelAttribute @Valid User user, Errors errors) {
        // creates user object


        if (errors.hasErrors()) {
            model.addAttribute("title", "Sign up!");
            model.addAttribute(user);
            return "user/add";


        } else if(user.getPassword().equals(user.getVerify())) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("title", "You're in!");
            return "user/index";

        } else {
            HashMap<String, String> passwordCheck = new HashMap<>();
            passwordCheck.put("verifyError", "Passwords do not match");
            model.addAttribute("verifyError", passwordCheck);
            model.addAttribute("title", "Sign up!");
            model.addAttribute(user);
            return "user/add";


        }
    }
}
