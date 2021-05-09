package com.myledger.controller.urja;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UrjaInnovationsController {

    @GetMapping("/urja-innovations/urja-home-page")
    public String showUrjaHomepage(Model model){

        return "urja_innovations/urja_home_page";
    }
}
