package com.kentotechblog.blogs.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HomeCategoryDTO {
	
	int categoryNum;
	int categoryId;
	String categoryName;
	
	public  HomeCategoryDTO(int categoryNum, String categoryName, int categoryId) {
		this.categoryNum = categoryNum;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
}
