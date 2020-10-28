package com.packsendme.roadbrewa.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.roadbrewa.category.dao.Category_Dao;
import com.packsendme.roadbrewa.category.dto.CategoryListResponse_Dto;
import com.packsendme.roadbrewa.component.RoadwayManagerConstants;
import com.packsendme.roadbrewa.dto.CategoryDto;
import com.packsendme.roadbrewa.entity.Category;

@Service
@ComponentScan({"com.packsendme.roadbrewa.category.dao"})
public class Category_Service {
	
	@Autowired
	private Category_Dao category_DAO;
	
	private CategoryDto categoryObj = new CategoryDto();

	public ResponseEntity<?> findAll() {
		Response<CategoryListResponse_Dto> responseObj = null;
		CategoryListResponse_Dto categoryListResponse_Dto = new CategoryListResponse_Dto();
		try {
			categoryListResponse_Dto.categories = categoryObj.entityTOdto(category_DAO.findAll());
			responseObj = new Response<CategoryListResponse_Dto>(0,HttpExceptionPackSend.CREATED_ROADWAYBRE.getAction(), categoryListResponse_Dto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<CategoryListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> save(CategoryDto categoryDto) {
		Response<CategoryDto> responseObj = null;
		try {
			Category entity = categoryObj.Dto_TO_Entity(categoryDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
			category_DAO.save(entity);
			responseObj = new Response<CategoryDto>(0,HttpExceptionPackSend.CREATE_ROADWAYBRE.getAction(), categoryDto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<CategoryDto>(0,HttpExceptionPackSend.CREATE_ROADWAYBRE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> delete(String id) {
		Response<String> responseObj = null;
		try {
			Optional<Category> categoryData = category_DAO.findOneById(id);
			if(categoryData.isPresent()) {
				Category entity = categoryData.get(); 
				if(category_DAO.remove(entity) == true) {
					responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_VEHICLE.getAction(), id);
				}
				else {
					responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_VEHICLE.getAction(), id);
					return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
				}
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_ROADWAYBRE.getAction(), id);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> preparedUpdate(String id, CategoryDto categoryDto) {
		Response<Category> responseObj = null;
		try {
			Category catCheckModel = category_DAO.findOneByName(categoryDto.name_category);

			if(catCheckModel == null){
				return update(id, categoryDto); 
			}
			else if(catCheckModel.id.equals(id)) {
				return update(id, categoryDto); 
			}
			else if(!catCheckModel.id.equals(id)) {
				System.out.println(" preparedUpdateCategory ID != ID "+ catCheckModel.id );

				responseObj = new Response<Category>(0,HttpExceptionPackSend.UPDATE_CATEGORY.getAction(), null);
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
			return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<Category>(0,HttpExceptionPackSend.UPDATE_CATEGORY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> update(String id, CategoryDto categoryDto) {
		Response<String> responseObj = null;
		try {
			Optional<Category> categoryData = category_DAO.findOneById(id);
			if(categoryData.isPresent()) {
				Category entity = categoryData.get(); 
				entity = categoryObj.Dto_TO_Entity(categoryDto, entity, RoadwayManagerConstants.UPDATE_OP_ROADWAY);
				entity = category_DAO.update(entity);
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_ROADWAY.getAction(), entity.id);
				return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_ROADWAY.getAction(), null);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public ResponseEntity<?> findCategoriesByTransports(String name) {
		Response<CategoryListResponse_Dto> responseObj = null;
		CategoryListResponse_Dto categoryListDTO_Response = new CategoryListResponse_Dto();

		try {
			List<Category> categories_L = category_DAO.findEntityByParameters(name);
			categoryListDTO_Response.categories = categoryObj.entityTOdto(categories_L);
			responseObj = new Response<CategoryListResponse_Dto>(0,HttpExceptionPackSend.CREATED_CATEGORY.getAction(), categoryListDTO_Response);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<CategoryListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	public ResponseEntity<?> crudTrigger(String operationType, String categoryName_Old, CategoryRuleModel categoryModelNew) {
		RoadwayModel categoryObj_Model = new RoadwayModel();
		Response<CategoryRuleModel> responseObj = null;
	
		// Find Category relationship with Vehicle will be removed
		try {
			List<RoadwayModel> roadwayL = roadwayBRE_DAO.findAll();
			for(RoadwayModel roadway_entity : roadwayL) {
				
				if(operationType.equals(RoadwayManagerConstants.DELETE_OP_ROADWAY)) {
					if(roadway_entity.categoryRule.categoryType.name_category.equals(categoryModelNew.categoryType.name_category)) {
						categoryObj_Model = roadway_entity;
						roadwayBRE_DAO.remove(categoryObj_Model);
						categoryObj_Model = new RoadwayModel();
					}
				} 
				else if(operationType.equals(RoadwayManagerConstants.UPDATE_OP_ROADWAY)) {
					if(roadway_entity.categoryRule.categoryType.name_category.equals(categoryName_Old)) {
						categoryObj_Model = roadway_entity;
						categoryObj_Model.categoryRule = null;
						categoryObj_Model.categoryRule = categoryModelNew;
						roadwayBRE_DAO.update(categoryObj_Model);
						categoryObj_Model = new RoadwayModel();
					}
				}
			}
			responseObj = new Response<CategoryRuleModel>(0,HttpExceptionPackSend.UPDATE_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<CategoryRuleModel>(0,HttpExceptionPackSend.UPDATE_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}*/
	
}
