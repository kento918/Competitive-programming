package com.kentotechblog.blogs.dto;

import java.time.LocalDateTime;

import com.kentotechblog.blogs.entity.PostEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AllPostDTO {
	int postId;
	String UserName;
	String PostTitle;
	String PostBodyHeading;
	LocalDateTime createdAt;
	
	
	public AllPostDTO(PostEntity post, String username) {
		this.postId = post.getId();
		this.UserName = username;
		this.PostTitle = post.getTitle();
		this.PostBodyHeading = post.getBody();
		this.createdAt = post.getCreatedAt();
	}
}
