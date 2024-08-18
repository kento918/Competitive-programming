package com.kentotechblog.blogs.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CategoryEntity {
	@Id
	int id;
	String name;
	String description;
	int parent_id;
	LocalDateTime created_at;
	LocalDateTime updated_at;
	int order_id;
	String image;
	
	public String toString() {
		return this.id +
				this.name+
				this.description+
				this.parent_id+
				this.created_at+
				this.updated_at+
				this.order_id+
				this.image;
	}
}
