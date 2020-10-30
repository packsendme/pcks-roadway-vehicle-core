package com.packsendme.roadbrewa.vehicle.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadbrewa.dto.VehicleTypeDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class VehicleTypeListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<VehicleTypeDto> vehiclesType = new ArrayList<VehicleTypeDto>();

	public VehicleTypeListResponse_Dto(List<VehicleTypeDto> vehiclesType) {
		super();
		this.vehiclesType = vehiclesType;
	}

	public VehicleTypeListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
