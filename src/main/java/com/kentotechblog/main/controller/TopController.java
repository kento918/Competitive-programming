package com.kentotechblog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {
	
	@GetMapping("/")
	public String topHome() {
		return "main";
	}
	@GetMapping("/home")
	public String topHome2() {
		return "main";
	}
	@GetMapping("/main/about")
	public String topAbout() {
		return "main/about";
	}
	@GetMapping("main/products")
	public String toProds() {
		return "main/products";
	}

}
