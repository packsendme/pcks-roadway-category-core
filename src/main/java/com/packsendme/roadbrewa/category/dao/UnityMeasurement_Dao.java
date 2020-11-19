package com.packsendme.roadbrewa.category.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadbrewa.category.repository.IUnityMeasurement_Repository;
import com.packsendme.roadbrewa.entity.UnityMeasurement;

@Component
@ComponentScan({"com.packsendme.microservice.manager.roadway.repository"})
public class UnityMeasurement_Dao implements ICrud_Dao<UnityMeasurement> {

	@Autowired
	IUnityMeasurement_Repository unitMeasurement_Rep; 
		
	@Override
	public UnityMeasurement save(UnityMeasurement entity) {
		try {
			return entity = unitMeasurement_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<UnityMeasurement> findOneById(String id) {
		try {
			return unitMeasurement_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

 
	@Override
	public Boolean remove(UnityMeasurement entity) {
		try {
			unitMeasurement_Rep.delete(entity);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public UnityMeasurement update(UnityMeasurement entity) {
		try {
			UnityMeasurement entityModel = unitMeasurement_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public UnityMeasurement findOneByName(String name) {
		try {
			return unitMeasurement_Rep.findUnityMeasurementByTypeUnity(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UnityMeasurement findOneByIdAndName(String id, String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UnityMeasurement> findAll() {
		try {
			List<UnityMeasurement> entityL = unitMeasurement_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UnityMeasurement> findEntityByParameters(String name) {
		// TODO Auto-generated method stub
		return null;
	}
 
	
}
