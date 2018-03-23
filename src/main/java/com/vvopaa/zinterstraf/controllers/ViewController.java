package com.vvopaa.zinterstraf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = "/")
    public String startPage() {

        return "index.html";
    }



}
