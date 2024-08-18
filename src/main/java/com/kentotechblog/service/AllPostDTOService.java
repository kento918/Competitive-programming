package com.kentotechblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kentotechblog.blogs.dto.AllPostDTO;
import com.kentotechblog.blogs.entity.PostEntity;

@Service
public class AllPostDTOService {
	@Autowired
	UserService us;
	@Autowired
	PostService ps;


	int titleMessageLength=40;
	int bodyMessageLength = 100;

	public AllPostDTO createAllPostDTOInstance(PostEntity post, String username) {
		return new AllPostDTO(post, username);
	}

	public List<AllPostDTO> createAllPost() {
		List<AllPostDTO> postDTOList = new ArrayList<>();
		List<PostEntity> postEntitiys = ps.getAll();
		
		for (PostEntity post : postEntitiys) {
			if(post.getTitle().length() >= titleMessageLength) {
				post.setTitle(post.getTitle().substring(0,titleMessageLength));
			}
			if(post.getBody().length() >= bodyMessageLength) {
				post.setBody(post.getBody().substring(0,bodyMessageLength)+" ...");
			}
			postDTOList.add(createAllPostDTOInstance(post, us.getNameById(post.getAuthorId())));
		}
		return postDTOList;
	}

	public List<AllPostDTO> createCategoryPost(int id) {
		List<AllPostDTO> postDTOList = new ArrayList<>();
		List<PostEntity> postEntitiys = ps.getAll();
		
		for (PostEntity post : postEntitiys) {
			if(post.getCategoryId() == id) {
				if(post.getTitle().length() >= titleMessageLength) {
					post.setTitle(post.getTitle().substring(0,titleMessageLength));
				}
				if(post.getBody().length() >= bodyMessageLength) {
					post.setBody(post.getBody().substring(0,bodyMessageLength)+" ...");
				}
				postDTOList.add(createAllPostDTOInstance(post, us.getNameById(post.getAuthorId())));
			}
		}
		return postDTOList;
	}
}
