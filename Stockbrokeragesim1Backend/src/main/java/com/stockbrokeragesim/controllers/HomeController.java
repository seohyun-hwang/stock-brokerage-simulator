package com.stockbrokeragesim.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/") // website root
    public String returnIndexHTML() {
        return "Hello World (index.html)";
    }
}

