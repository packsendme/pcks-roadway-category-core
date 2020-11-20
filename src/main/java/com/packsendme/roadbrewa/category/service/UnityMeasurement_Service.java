package com.packsendme.roadbrewa.category.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.roadbrewa.category.dao.UnityMeasurement_Dao;
import com.packsendme.roadbrewa.category.dto.UnityMeasurementListResponse_Dto;
import com.packsendme.roadbrewa.component.RoadwayManagerConstants;
import com.packsendme.roadbrewa.dto.UnityMeasurementDto;
import com.packsendme.roadbrewa.entity.UnityMeasurement;

@Service
@ComponentScan({"com.packsendme.roadbrewa.category.dao"})
public class UnityMeasurement_Service {

	@Autowired
	private UnityMeasurement_Dao unityDAO;
	
	private UnityMeasurementDto unityObj = new UnityMeasurementDto();

	
	public ResponseEntity<?> findAll() {
		Response<UnityMeasurementListResponse_Dto> responseObj = null;
		UnityMeasurementListResponse_Dto unityListDTO_Response = new UnityMeasurementListResponse_Dto();
		try {
			unityListDTO_Response.unityMeasurements = unityObj.entityTOdto_L(unityDAO.findAll());
			responseObj = new Response<UnityMeasurementListResponse_Dto>(0,HttpExceptionPackSend.CREATED_UNITY_MEASUREMENT.getAction(), unityListDTO_Response);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<UnityMeasurementListResponse_Dto>(0,HttpExceptionPackSend.CREATED_UNITY_MEASUREMENT.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> findUnityMeasurementByTypUnity(String unity) {
		Response<UnityMeasurementDto> responseObj = null;
		try {
			UnityMeasurementDto unityDto = unityObj.entityTOdto(unityDAO.findOneByName(unity));
			responseObj = new Response<UnityMeasurementDto>(0,HttpExceptionPackSend.CREATED_UNITY_MEASUREMENT.getAction(), unityDto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<UnityMeasurementDto>(0,HttpExceptionPackSend.CREATED_UNITY_MEASUREMENT.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}

	
	public ResponseEntity<?> save(UnityMeasurementDto unityDto) {
		Response<UnityMeasurementDto> responseObj = null;
		try {
			if(unityDAO.findOneByName(unityDto.unityType) == null) {
				UnityMeasurement entity = unityObj.dtoTOentity(unityDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
				unityDAO.save(entity);
				responseObj = new Response<UnityMeasurementDto>(0,HttpExceptionPackSend.CREATED_UNITY_MEASUREMENT.getAction(), unityDto);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<UnityMeasurementDto>(0,HttpExceptionPackSend.CREATED_UNITY_MEASUREMENT.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> delete(String id) {
		Response<String> responseObj = null;
		try {
			Optional<UnityMeasurement> unityData = unityDAO.findOneById(id);
			if (unityData.isPresent()) {
				UnityMeasurement entity = unityData.get();
				if(unityDAO.remove(entity) == true) {
					responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_UNITY_MEASUREMENT.getAction(), unityData.get().id);
				}
			}
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_UNITY_MEASUREMENT.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> update(String id, UnityMeasurementDto unityDto) {
		Response<String> responseObj = null;
		try {
			if(unityDAO.findOneByName(unityDto.unityType) == null) {
				Optional<UnityMeasurement> unityData = unityDAO.findOneById(id);
				UnityMeasurement entity = unityObj.dtoTOentity(unityDto, unityData.get(), RoadwayManagerConstants.UPDATE_OP_ROADWAY);
				entity = unityDAO.update(entity);
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_UNITY_MEASUREMENT.getAction(), entity.id);
				return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
