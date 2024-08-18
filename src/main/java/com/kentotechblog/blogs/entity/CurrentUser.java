package com.kentotechblog.blogs.entity;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class CurrentUser extends User{
	public CurrentUser getCurrentUser() {
		return new CurrentUser();
	}

}
