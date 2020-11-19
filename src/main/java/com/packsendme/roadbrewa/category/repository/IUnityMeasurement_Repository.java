package com.packsendme.roadbrewa.category.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.UnityMeasurement;

@Repository
public interface IUnityMeasurement_Repository extends MongoRepository<UnityMeasurement, String>{
	
	@Query("{'typeUnity' :  {$eq: ?0}}")
	UnityMeasurement findUnityMeasurementByTypeUnity(String typeUnity);
	
}
