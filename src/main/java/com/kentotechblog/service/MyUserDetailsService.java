package com.kentotechblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kentotechblog.blogs.entity.UserEntity;
import com.kentotechblog.blogs.mapper.UserMapper;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper mp;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		if (mp.isGetUserName(username)) {
			
			UserEntity user = mp.getUserByName(username);

			return User.builder()
					.username(user.getName())
					.password(user.getPassword()/* + user.getPassword_salt()*/)
					.roles(getRoles(user))
					.build();

		} else {
			throw new UsernameNotFoundException(username);
		}
	}

	private String[] getRoles(UserEntity user) {
		if (user.getRoles().equals("admin")) {
			return new String[] { "admin", "user" };
		} else if (user.getRoles().isEmpty() || user.getRoles().equals("user")) {
			return new String[] { "user" };

		} else {
			return new String[] { "user" };
		}
	}
}
