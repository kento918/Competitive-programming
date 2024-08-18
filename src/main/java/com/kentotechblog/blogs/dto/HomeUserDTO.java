package com.kentotechblog.blogs.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class HomeUserDTO {

	String username;
	String roles;

	public HomeUserDTO(String username, String roles) {
		this.username = username;
		this.roles = roles;
	}
}
