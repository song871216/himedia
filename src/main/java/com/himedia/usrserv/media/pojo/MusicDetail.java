package com.himedia.usrserv.media.pojo;

import javax.validation.constraints.NotEmpty;

public class MusicDetail {
	
	Long id;
	
	@NotEmpty
	String name;

	@NotEmpty
	String category;
	
	String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
