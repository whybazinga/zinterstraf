package com.vvopaa.zinterstraf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String startPage() {
        return "index";
    }

    @GetMapping("/new")
    public String startPageNew() {
        return "mine";
    }
}
