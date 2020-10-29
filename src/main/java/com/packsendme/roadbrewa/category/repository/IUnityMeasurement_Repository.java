package com.packsendme.roadbrewa.category.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.UnityMeasurement;

@Repository
public interface IUnityMeasurement_Repository extends MongoRepository<UnityMeasurement, String>{
	
	@Query("{'origin_country' :  {$eq: ?0}}")
	List<UnityMeasurement> findUnityMeasurementByCountry(String origin_country);

	@Query("{'unitMeasurement' :  {$eq: ?0}}")
	UnityMeasurement findUnityMeasurementByName(String unitMeasurement);

}
