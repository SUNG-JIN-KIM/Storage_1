package com.k02.storage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Storagecontroller {

    @GetMapping({"/", "/storage"})
    public String storage() {
        return "storage";
    }
}
