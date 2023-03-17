package com.example.mvccrud.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BooksController {
    @GetMapping("/")
    public String index(){
        return "author-form";
    }
}
