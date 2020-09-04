package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupPage() {
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        String signupError = null;
        if (this.userService.isUserPresent(user.getUsername())) {
            signupError = "User already present, please try to login.";
            model.addAttribute("signupError", signupError);
            return "signup";
        }

        if (signupError == null) {
            int numRowsAdded = this.userService.createUser(user);
            if (numRowsAdded < 0) {
                signupError = "Something went wrong, please try again later.";
                model.addAttribute("signupError", signupError);
                return "signup";
            } else {
                model.addAttribute("signupSuccess", true);
                return "redirect:/login";
            }
        }

        return "signup";
    }
}
