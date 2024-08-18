package com.kentotechblog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kentotechblog.blogs.dto.HomeCategoryDTO;
import com.kentotechblog.blogs.entity.CategoryEntity;

@Service
public class HomeCategoryDTOService {
	
	@Autowired
	PostService ps;
	
	@Autowired
	CategoryService cs;
	
	public List<HomeCategoryDTO> createCategoryList(){
		List<CategoryEntity> allCate = cs.getCategoryAll();
		ArrayList<Integer> categoryNums= new ArrayList<>();
		for(CategoryEntity cate : allCate) {
			categoryNums.add(ps.getCategoryCounter(cate.getId()));
		}
		List<HomeCategoryDTO> ent = new ArrayList<>();
		for(int i = 0; i < categoryNums.size(); i++) {
			CategoryEntity c = allCate.get(i);
			ent.add(new HomeCategoryDTO(categoryNums.get(i), c.getName(), c.getId()));
		}
		
		return ent;
		
	}

}
