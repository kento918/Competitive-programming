package com.kentotechblog.service;

import org.springframework.stereotype.Service;

import com.kentotechblog.blogs.dto.ShowPostDTO;
import com.kentotechblog.blogs.entity.PostEntity;

@Service
public class ShowPostDTOService {

	public static ShowPostDTO createShowPost(PostEntity post, String categoryName, String userName, String authName) {
		ShowPostDTO posts = new ShowPostDTO();
		posts.setId(post.getId());
		posts.setTitle(post.getTitle());
		posts.setBody(post.getBody());
		posts.setUserName(userName);
		posts.setCreatedAt(post.getCreatedAt());
		posts.setCategoryName(categoryName);
		posts.setWriter(isWriter(authName, userName));
		return posts;
	}
	
	private static boolean isWriter(String authName, String postName) {
		return authName != null && postName != null && authName.equals(postName);
	}
}
