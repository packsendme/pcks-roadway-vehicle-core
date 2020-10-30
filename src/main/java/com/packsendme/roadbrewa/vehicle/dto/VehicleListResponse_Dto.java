package com.packsendme.roadbrewa.vehicle.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadbrewa.dto.VehicleDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class VehicleListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<VehicleDto> vehicles = new ArrayList<VehicleDto>();

 

	public VehicleListResponse_Dto(List<VehicleDto> vehicles) {
		super();
		this.vehicles = vehicles;
	}



	public VehicleListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
