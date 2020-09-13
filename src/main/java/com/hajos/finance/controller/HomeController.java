package com.hajos.finance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String index(){
        return "statement";
    }

    @RequestMapping("/costs")
    public String costs(){
        return "costs";
    }
    @RequestMapping("/costtypes")
    public String costtypes(){
        return "costtypes";
    }
    @RequestMapping("/income")
    public String income(){
        return "income";
    }
}
