package com.kentotechblog.blogs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kentotechblog.blogs.entity.UserEntity;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM user WHERE id = #{id}")
	UserEntity getUserById(int id);
	
	@Insert("INSERT INTO user (name, password, password_salt, roles) VALUES(#{name}, #{password}, #{password_salt}, #{roles})")
	void insertUser(UserEntity user);
	
	@Select("SELECT * FROM user")
	List<UserEntity> getAllUser();
	
	@Select("SELECT name FROM user WHERE id = #{id}")
	String getNameById(int id);

	@Select("SELECT EXISTS(SELECT * FROM user WHERE name = #{username})")
	boolean isGetUserName(String username);
	
	@Select("SELECT * FROM user WHERE name = #{username}")
	UserEntity getUserByName(String username);
	
	@Select("SELECT name FROM user WHERE id IN 1")
	UserEntity getGuestData();

	@Select("SELECT id FROM user WHERE name = #{name}")
	int getIdByUserName(String name);
}
