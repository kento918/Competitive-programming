package com.kentotechblog.blogs.controller;

import java.io.IOException;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.kentotechblog.blogs.entity.CategoryEntity;
import com.kentotechblog.blogs.entity.PostEntity;
import com.kentotechblog.blogs.entity.UserEntity;
import com.kentotechblog.service.CategoryService;
import com.kentotechblog.service.PostService;
import com.kentotechblog.service.ShowPostDTOService;
import com.kentotechblog.service.StringToHtmlService;
import com.kentotechblog.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PostController {
	@Autowired
	UserService userService;

	@Autowired
	PostService postService;

	@Autowired
	CategoryService cateService;

	@GetMapping("/blogs/registPost")
	public String registPost(Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserEntity ue = userService.getUserByName(auth.getName());
			List<CategoryEntity> cateList = cateService.getCategoryAll();
			model.addAttribute("cate", cateList);
			model.addAttribute("post", new PostEntity());
			model.addAttribute("user", ue);

			log.info("create new post " + auth.getName());
			return "blogs/post/registPost";
		} catch (Exception e) {
			log.error(e.getMessage());
			return "blogs/login";
		}
	}

	@PostMapping("/blogs/registerPost")
	public void createNewPost(@ModelAttribute PostEntity post, @RequestParam("category") int categoryId, Model model,
			HttpServletResponse response) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserEntity user = userService.getUserByName(auth.getName());
			post.setCategoryId(categoryId);
			post.setBody(StringToHtmlService.toHtml(post.getBody()));
			postService.savePost(post, user);
			log.info("save post " + auth.getName() + " succsess");

			if (auth.toString().contains("admin")) {
				response.sendRedirect("/blogs/adminHome");
			} else if (auth.toString().contains("user")) {
				response.sendRedirect("/blogs/userHome");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			try {
				response.sendRedirect("/blogs/home");
			} catch (IOException e1) {
				log.error(e1.getMessage());
			}
		}
	}

	@GetMapping("/blogs/post/{id}")
	public String showPosts(@PathVariable Long id, Model model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			PostEntity post = postService.getPostByPostid(id);
			CategoryEntity cate = cateService.getCategoryById(post.getCategoryId());
			String userName = userService.getNameById(post.getAuthorId());

			model.addAttribute("post",
					ShowPostDTOService.createShowPost(post, cate.getName(), userName, auth.getName()));
			return "blogs/post/showPost";
		} catch (Exception e) {
			log.error(e.getMessage());
			return "blogs/login";
		}
	}

	@GetMapping("/blogs/updatePost/{id}")
	public String toUpdatePost(@PathVariable Long id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PostEntity post = postService.getPostByPostid(id);
		post.setBody(StringToHtmlService.toMarkdown(post.getBody()));
		String cateName = "";
		String userName = userService.getNameById(post.getAuthorId());
		List<CategoryEntity> cateList = cateService.getCategoryAll();

		model.addAttribute("cate", cateList);
		model.addAttribute("post", ShowPostDTOService.createShowPost(post, cateName, userName, auth.getName()));

		return "blogs/post/updatePost";
	}

	@PostMapping("/blogs/updatePost/{id}")
	public void updatePost(@PathVariable Long id, @ModelAttribute PostEntity post, Model model, HttpServletResponse response) {
		String title = post.getTitle();
		String body = post.getBody();
		LocalDateTime updateAt = LocalDateTime.now();

		postService.updatePost(title, body, updateAt, id);
		log.info("update!");
		try {
			response.sendRedirect("/blogs/home");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/blogs/deletePost/{id}")
	public String toDeletePost(@PathVariable Long id, Model model) {
		postService.deletepost(id);
		return "blogs/post/deletePost";
	}
}
