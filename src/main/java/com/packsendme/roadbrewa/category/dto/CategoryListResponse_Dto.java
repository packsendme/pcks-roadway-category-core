package com.packsendme.roadbrewa.category.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadbrewa.dto.CategoryDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class CategoryListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<CategoryDto> categories = new ArrayList<CategoryDto>();

 

	public CategoryListResponse_Dto(List<CategoryDto> categories) {
		super();
		this.categories = categories;
	}



	public CategoryListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
