package com.stockbrokeragesim.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {
    @RequestMapping("/") // website root
    public String returnIndexHTML() {
        return "Hello World (index.html)";
    }
}

