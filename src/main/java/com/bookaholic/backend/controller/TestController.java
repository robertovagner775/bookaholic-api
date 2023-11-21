package com.bookaholic.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    

    @GetMapping("/aa")
    public String testApi() {
        return "Olá a api está funcionando";
    }
}
