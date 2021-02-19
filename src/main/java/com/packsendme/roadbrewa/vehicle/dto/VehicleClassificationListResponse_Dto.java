package com.packsendme.roadbrewa.vehicle.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadbrewa.dto.VehicleClassificationDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class VehicleClassificationListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<VehicleClassificationDto> vehiclesCategory = new ArrayList<VehicleClassificationDto>();

	public VehicleClassificationListResponse_Dto(List<VehicleClassificationDto> vehiclesCategory) {
		super();
		this.vehiclesCategory = vehiclesCategory;
	}

	public VehicleClassificationListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
