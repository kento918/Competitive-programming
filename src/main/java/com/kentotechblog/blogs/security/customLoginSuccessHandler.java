package com.kentotechblog.blogs.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class customLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
		if(auth.toString().contains("admin")) {
			log.info("login success ADMIN " + authentication.getName());
			response.sendRedirect("/blogs/adminHome");
			
		}else {
			log.info("login success USER " + authentication.getName());
			response.sendRedirect("/blogs/userHome");
		}
	}

}
