package com.packsendme.roadbrewa.category.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.Category;

@Repository
public interface ICategory_Repository extends MongoRepository<Category, String>{

	@Query("{'name_category' :  {$eq: ?0}}")
	Category findCategoryByName(String category_name);
	
	@Query("{'transport.name_category' :  {$eq: ?0}}")
	List<Category> findCategoryByTransport(String transport);
	
}
