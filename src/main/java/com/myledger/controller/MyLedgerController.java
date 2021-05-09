package com.myledger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyLedgerController {

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }
}
