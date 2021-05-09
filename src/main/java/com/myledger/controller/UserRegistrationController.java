package com.myledger.controller;

import com.myledger.model.UserRegistration;
import com.myledger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;


    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new UserRegistration());
        return "sign_up";
    }

    @PostMapping("/sign-up")
    public String registerUser(@ModelAttribute("user") UserRegistration userRegistration){
        userService.saveUser(userRegistration);
        return "redirect:/sign-up?success";
    }

}
