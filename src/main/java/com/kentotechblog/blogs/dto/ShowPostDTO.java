package com.kentotechblog.blogs.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowPostDTO {
	int id;
	String title;
	String userName;
	LocalDateTime createdAt;
	String categoryName;
	String body;
	boolean isWriter;

}
