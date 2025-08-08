package com.gtalent.tutor.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return"Hello,World";
    }
    @GetMapping("/number")
    public String number(){
        return"123";
    }
    @GetMapping("/hello2")
    public String hello2(){
        return ("hello2.html");
    }


    @GetMapping("/vl/rest/datastore/F-c0032-001")
    public String forecasrWeather(){
        return"台中市明天天氣晴";
    }
    @PostMapping("/post")
    public String postTest(){
        return "post";
    }
}

