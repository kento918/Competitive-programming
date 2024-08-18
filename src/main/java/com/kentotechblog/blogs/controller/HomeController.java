package com.kentotechblog.blogs.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kentotechblog.blogs.dto.AllPostDTO;
import com.kentotechblog.blogs.dto.HomeCategoryDTO;
import com.kentotechblog.blogs.dto.HomePostDTO;
import com.kentotechblog.blogs.dto.HomeUserDTO;
import com.kentotechblog.service.AllPostDTOService;
import com.kentotechblog.service.HomeCategoryDTOService;
import com.kentotechblog.service.HomeDTOService;
import com.kentotechblog.service.PostService;
import com.kentotechblog.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@Autowired
	UserService us;

	@Autowired
	PostService ps;

	@Autowired
	HomeCategoryDTOService cateDTO;

	@Autowired
	AllPostDTOService apds;

	@Autowired
	HomeDTOService HomeDTOS;

	@GetMapping("/blogs")
	public String top(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean flug = false;
		if (auth.getName() != "anonymousUser") {
			flug = true;
		}

		List<AllPostDTO> list = apds.createAllPost();
		List<HomeCategoryDTO> cate = cateDTO.createCategoryList();
		list = list.stream().limit(12).collect(Collectors.toList());

		model.addAttribute("post", list);
		model.addAttribute("flug", flug);
		model.addAttribute("cate", cate);
		if (flug) {
			log.info(String.format("hello %s!", auth.getName()));
			model.addAttribute("username", auth.getName());
		} else
			log.info("comeing guest!");
		return "blogs/top";
	}

	@PostMapping("/blogs")
	public String posttop() {
		return "blogs/top";
	}

	@GetMapping("/blogs/userHome")
	public String userHome(Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userName = auth.getName();
			log.info(userName + " come home");
			List<HomePostDTO> posts = HomeDTOS.createHomePostDTO(userName);
			posts = posts.stream().limit(12).collect(Collectors.toList());
			HomeUserDTO user = HomeDTOS.createHomeUserDTO(userName);
			model.addAttribute("username", auth.getName());
			model.addAttribute("user", user);
			model.addAttribute("posts", posts);
			return "blogs/user/home";
		} catch (Exception e) {
			log.error(e.getMessage());
			return "blogs/login";
		}
	}

	@GetMapping("/blogs/adminHome")
	public String adminHome(Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userName = auth.getName();
			log.info(userName + " come home");
			List<HomePostDTO> posts = HomeDTOS.createHomePostDTO(userName);
			HomeUserDTO user = HomeDTOS.createHomeUserDTO(userName);
			model.addAttribute("username", auth.getName());
			model.addAttribute("user", user);
			model.addAttribute("posts", posts);
			return "blogs/admin/home";
		} catch (Exception e) {
			log.error(e.getMessage());
			return "blogs/login";
		}
	}

	@GetMapping("/blogs/home")
	public void homeHandler(HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			if (auth.toString().contains("admin")) {
				response.sendRedirect("/blogs/adminHome");
			} else if (auth.toString().contains("user")) {
				response.sendRedirect("/blogs/userHome");
			}
		} catch (IOException e) {
			log.warn(e.getMessage());
			e.printStackTrace();
		}
	}
}
