package com.packsendme.roadway.category.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadway.commons.entity.Category;
import com.packsendme.roadway.category.repository.ICategory_Repository;

@Component
@ComponentScan({"com.packsendme.roadway.category.repository"})
public class Category_Dao implements ICrud_Dao<Category> {

	@Autowired
	ICategory_Repository roadwayManager_Rep; 
	
	
	@Override
	public Category save(Category entity) {
		try {
			return entity = roadwayManager_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<Category> findOneById(String id) {
		try {
			return roadwayManager_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Category> findAll() {
		try {
			List<Category> entityL = roadwayManager_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean remove(Category entity) {
		try {
			roadwayManager_Rep.delete(entity);
			return true; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return false; 
		}		
	}

	@Override
	public Category update(Category entity) {
		try {
			Category entityModel = roadwayManager_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public Category findOneByName(String name) {
		try {
			return roadwayManager_Rep.findCategoryByName(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Category findOneByIdAndName(String id, String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Category> findEntityByParameters(String name) {
		try {
			return roadwayManager_Rep.findCategoryByTransport(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}
}
