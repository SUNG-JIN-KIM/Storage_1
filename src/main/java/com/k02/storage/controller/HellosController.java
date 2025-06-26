package com.k02.storage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HellosController {

    @GetMapping("/hellos")
    public String hellos(){
        return "hellos";
    }

    @GetMapping("/ksj")
    public String ksj(){
        return "ksj";
    }

    @GetMapping("/guess")
    public String guess(){
        return "guess";
    }

    @GetMapping("/maze")
    public String maze(){
        return "maze";
    }

}
