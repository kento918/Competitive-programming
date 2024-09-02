package com.kentotechblog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kentotechblog.blogs.entity.PostEntity;
import com.kentotechblog.blogs.entity.UserEntity;
import com.kentotechblog.blogs.mapper.PostMapper;

@Service
public class PostService{
	
	@Autowired
	PostMapper postMapper;
	
	public void savePost(PostEntity post, UserEntity user) {
		post.setAuthorId(user.getId());
		postMapper.insertPost(post);
	}
	
	public List<PostEntity> getAll(){
		return postMapper.selectAll();
	}

	public List<PostEntity> getPostByAuthorId(int Authorid) {
		return postMapper.getPostByauthorId(Authorid);
	}

	public PostEntity getPostByPostid(Long id) {
		return postMapper.getPostByPostid(id);
	}
	
	public int getCategoryCounter(int id) {
		return postMapper.getCategoryCounter(id);
	}

	public List<PostEntity> getPostByCategoryId(int i) {
		return postMapper.getPostByCategoryId(i);
	}

	public void deletepost(Long id) {
		postMapper.deletePost(id);
	}

	public void updatePost(String title, String body, LocalDateTime updateAt, Long postId) {
		postMapper.updatePost(title, body, updateAt, postId);
	}
}
