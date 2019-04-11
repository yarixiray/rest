package com.rest.webservices.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world");
    }
}
