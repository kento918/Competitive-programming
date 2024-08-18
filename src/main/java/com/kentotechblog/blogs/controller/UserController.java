package com.kentotechblog.blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kentotechblog.blogs.entity.UserEntity;
import com.kentotechblog.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/blogs/registUser")
	public String createNewUser(Model model) {
		model.addAttribute("newUser", new UserEntity());
		return "blogs/registUser";
	}

	@PostMapping("/blogs/registUser")
	public String registerUser(@ModelAttribute UserEntity user, Model model) {
		userService.saveUser(user);
		return "redirect:blogs/login";
	}

}
