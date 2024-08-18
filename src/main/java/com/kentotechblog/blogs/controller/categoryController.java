package com.kentotechblog.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kentotechblog.blogs.dto.AllPostDTO;
import com.kentotechblog.blogs.dto.HomeCategoryDTO;
import com.kentotechblog.blogs.entity.CategoryEntity;
import com.kentotechblog.service.AllPostDTOService;
import com.kentotechblog.service.CategoryService;
import com.kentotechblog.service.HomeCategoryDTOService;

@Controller
public class categoryController {
	@Autowired
	CategoryService cate;
	
	@Autowired
	AllPostDTOService postDto;
	
	@Autowired
	HomeCategoryDTOService categoryDto;
	
	
	@GetMapping("/blogs/category/{id}")
	public String showCategoryList(@PathVariable int id, Model model ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean flug = false;
		if(auth.getName() != "anonymousUser") {
			flug = true;
		}
		CategoryEntity category = cate.getCategoryById(id);
		List<HomeCategoryDTO> cates = categoryDto.createCategoryList();
		List<AllPostDTO> posts = postDto.createCategoryPost(id);
		model.addAttribute("flug", flug);
		
		model.addAttribute("category",category);
		model.addAttribute("post",posts);
		model.addAttribute("categorys", cates);
		
		if(flug) {
			model.addAttribute("username", auth.getName());
		}
		
		return "blogs/category/showCategoryList";
	}
	
	@GetMapping("/blogs/addCategory")
	public String addCategory(Model model) {
		return "blogs/category/addCategory";
	}
	
	@PostMapping("/blogs/addCategory")
	public String insertCategory(@ModelAttribute CategoryEntity category, Model model) {
		category.setImage(null);
		category.setParent_id(0);
		cate.save(category);
		return "redirect:/blogs";
	}
	
}