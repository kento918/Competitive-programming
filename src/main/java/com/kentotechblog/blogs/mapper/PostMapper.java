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
	
	@Select("SELECT * FROM post order by createdAt desc")
	List<PostEntity> selectAll();
	
	@Select("select * from post \n"
		  + "where authorId = 30 \n"
		  + "order by createdAt desc" )
	List<PostEntity> getPostByauthorId(int authorid, String key);
	
	@Select("select * from post where id = #{id}")
	PostEntity getPostByPostid(Long id);
	
	@Select("select count(*) from post where categoryId = #{id}")
	int getCategoryCounter(int id);
	
	@Select("select * from post where categoryId = #{id} order by createdAt desc")
	List<PostEntity> getPostByCategoryId(int id);
	
	@Delete("delete from post where id = #{id}")
	void deletePost(Long id);
	
	@Update("update post set title = #{title},"
	        + "body = #{body},"         
	        + "updatedAt = #{updateAt} "
	        + "where id = #{postId} ")
	void updatePost(String title, String body, LocalDateTime updateAt, Long postId);
}