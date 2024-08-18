package com.kentotechblog.blogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/blogs/login")
	public String getLoginPage() {
		return "blogs/login";
	}
	
	@PostMapping("/blogs/login")
	public String getGuest() {
		return "blogs/login";
		
	}

	@GetMapping("/blogs/logout")
	public String toLogout() {
		return "blogs/logout";
	}
}
