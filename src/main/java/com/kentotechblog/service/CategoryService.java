package com.kentotechblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kentotechblog.blogs.entity.CategoryEntity;
import com.kentotechblog.blogs.mapper.CategoryMapper;

@Service
public class CategoryService{
	
	@Autowired
	CategoryMapper categoryMapper;
	
	public void save(CategoryEntity category) {
		categoryMapper.insertCategory(category);
	}

	public CategoryEntity getCategoryById(int i) {
		return categoryMapper.getCategoryById(i);
	}
	
	public List<CategoryEntity> getCategoryAll(){
		return categoryMapper.getCategoryAll();
	}

}
