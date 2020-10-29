package com.packsendme.roadbrewa.category.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadbrewa.dto.UnityMeasurementDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UnityMeasurementListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<UnityMeasurementDto> unityMeasurements = new ArrayList<UnityMeasurementDto>();

	public UnityMeasurementListResponse_Dto(List<UnityMeasurementDto> unityMeasurements) {
		super();
		this.unityMeasurements = unityMeasurements;
	}

	public UnityMeasurementListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
