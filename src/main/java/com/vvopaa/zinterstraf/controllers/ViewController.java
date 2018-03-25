package com.vvopaa.zinterstraf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

    @RequestMapping(value = "/", method={RequestMethod.GET})
    public String startPage() {

        return "index.html";
    }

}
