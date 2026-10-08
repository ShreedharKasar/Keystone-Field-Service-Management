package com.keystone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	  @GetMapping("/api/test")
	    public String test() {
	        return "JWT Authentication is working!";
	    }
}
