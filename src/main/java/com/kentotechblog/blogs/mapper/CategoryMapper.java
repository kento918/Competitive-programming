package com.kentotechblog.blogs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kentotechblog.blogs.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {
	@Select("SELECT * FROM category WHERE id = #{id}")
	CategoryEntity getCategoryById(int i);
	
	@Insert("INSERT INTO category (name, description, parent_id, order_id, image) "
			+ "VALUES(#{name}, #{description}, #{parent_id}, #{order_id}, #{image})")
	void insertCategory(CategoryEntity cate);
	
	@Select("select * from category")
	List<CategoryEntity> getCategoryAll();
}
