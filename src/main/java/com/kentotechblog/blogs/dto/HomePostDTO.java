package com.kentotechblog.blogs.dto;

import java.time.LocalDateTime;

import com.kentotechblog.blogs.entity.PostEntity;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class HomePostDTO {
	String postTitle;
	String body;
	String userName;
	LocalDateTime createdAt;
	int postId;

	public HomePostDTO(String name, PostEntity post){
		this.postTitle = post.getTitle();
		this.body = post.getBody();
		this.userName = name;
		this.createdAt = post.getCreatedAt();
		this.postId = post.getId();
	}
}
