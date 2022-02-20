package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiDeleteController {
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable(name="userId") String id, @RequestParam String account){

        System.out.println(id + " : " + account);
    }
}
