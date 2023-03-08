package com.example.wsmeverylisten.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        System.out.println("123");
        return "hello";
    }

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

    }

}
