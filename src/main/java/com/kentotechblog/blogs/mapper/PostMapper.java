package com.kentotechblog.blogs.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kentotechblog.blogs.entity.PostEntity;

@Mapper
public interface PostMapper {
	
	@Select("SELECT * FROM post WHERE authorId = #{authorId}")
	List<PostEntity> getPostById(int id);
	
	@Insert("INSERT INTO post(title, body, authorId, categoryId) VALUES (#{title}, #{body}, #{authorId}, #{categoryId})")
	void insertPost(PostEntity post);
	
	@Select("SELECT * FROM post ORDER BY createdAt DESC")
	List<PostEntity> selectAll();
	
	@Select("SELECT * FROM post \n"
		  + "WHERE authorId = #{authorid} \n"
		  + "ORDER BY createdAt DESC" )
	List<PostEntity> getPostByauthorId(int authorid);
	
	@Select("SELECT * FROM post WHERE id = #{id}")
	PostEntity getPostByPostid(Long id);
	
	@Select("SELECT count(*) FROM post WHERE categoryId = #{id}")
	int getCategoryCounter(int id);
	
	@Select("SELECT * FROM post WHERE categoryId = #{id} ORDER BY createdAt DESC")
	List<PostEntity> getPostByCategoryId(int id);
	
	@Delete("DELETE FROM post WHERE id = #{id}")
	void deletePost(Long id);
	
	@Update("UPDATE post SET title = #{title},"
	        + "body = #{body},"         
	        + "updatedAt = #{updateAt} "
	        + "WHERE id = #{postId} ")
	void updatePost(String title, String body, LocalDateTime updateAt, Long postId);
}