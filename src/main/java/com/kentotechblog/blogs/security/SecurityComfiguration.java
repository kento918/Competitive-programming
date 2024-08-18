package com.kentotechblog.blogs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.kentotechblog.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityComfiguration {
	
	@Autowired
	customLoginSuccessHandler customLoginSuccessHandler;
	
	@Autowired
	customLoginFailureHandler customLoginFailureHandler;

	@Autowired
	customLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Autowired
	private MyUserDetailsService us;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception{
		return https
				.csrf(cs -> cs.disable())
				.authorizeHttpRequests(registry->{
					registry.requestMatchers("/").permitAll();
					registry.requestMatchers("/home").permitAll();
					registry.requestMatchers("/main/**").permitAll();
					registry.requestMatchers("/blogs").permitAll();
					registry.requestMatchers("/blogs/login").permitAll();
					registry.requestMatchers("/blogs/logout").permitAll();
					registry.requestMatchers("/blogs/registUser").permitAll();
					registry.requestMatchers("/blogs/post/{id}").permitAll();
					registry.requestMatchers("/blogs/public.error").permitAll();
					registry.requestMatchers("/blogs/admin/**").hasRole("admin");
					registry.requestMatchers("/blogs/user/**").hasRole("user");
					registry.requestMatchers("/css/**").permitAll();
					registry.requestMatchers("/image/**").permitAll();
					registry.requestMatchers("/js/**").permitAll();
					
					registry.anyRequest().authenticated();
				})
				.formLogin(auth -> {
					auth.loginPage("/blogs/login").permitAll();
					auth.successHandler(customLoginSuccessHandler);
					auth.failureHandler(customLoginFailureHandler);
				})
				.logout(log ->{
					log.logoutUrl("/blogs/logout");
					log.logoutSuccessHandler(customLogoutSuccessHandler);
				})
				
				
		.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return us;
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 provider.setUserDetailsService(userDetailsService());
		 provider.setPasswordEncoder(passwordEncoder());
		 return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
