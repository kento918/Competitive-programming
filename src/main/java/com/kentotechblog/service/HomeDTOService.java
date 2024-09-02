package com.kentotechblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kentotechblog.blogs.dto.HomePostDTO;
import com.kentotechblog.blogs.dto.HomeUserDTO;
import com.kentotechblog.blogs.entity.PostEntity;
import com.kentotechblog.blogs.entity.UserEntity;

@Service
public class HomeDTOService {
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	int titleMessageLength = 20;
	int bodyMessageLength = 100;
	
	public List<HomePostDTO> createHomePostDTO(String username) {
		int authorId = userService.getIdByUserName(username);
		List<PostEntity> posts = postService.getPostByAuthorId(authorId);
		List<HomePostDTO> postList = new ArrayList<>();
		for(PostEntity p : posts) {
			p.setTitle(StringToHtmlService.cutHtmlMark(p.getTitle(),0));
			p.setBody(StringToHtmlService.cutHtmlMark(p.getBody(),1));
			postList.add(new HomePostDTO(username, p));
		}
		return postList;
	}

	public HomeUserDTO createHomeUserDTO(String name) {
		if(userService.isGetUserName(name)) {
			UserEntity user = userService.getUserByName(name);
			return new HomeUserDTO(user.getName(), user.getRoles());
		}
		return null;
	}
	
	public List<HomePostDTO> createCategoryPost(int categoryId){
		List<PostEntity> posts = postService.getPostByCategoryId(categoryId);
		List<HomePostDTO> list = new ArrayList<>();
		for(PostEntity p : posts) {
			p.setTitle(StringToHtmlService.cutHtmlMark(p.getTitle(),0));
			p.setBody(StringToHtmlService.cutHtmlMark(p.getBody(),1));
			list.add(new HomePostDTO(userService.getNameById(p.getAuthorId()), p));
		}
		return list;
	}
}
