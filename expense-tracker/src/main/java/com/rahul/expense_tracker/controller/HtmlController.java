package com.rahul.expense_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/")
    public String home() {
        return "index"; // returns index.html from templates/
    }

}
