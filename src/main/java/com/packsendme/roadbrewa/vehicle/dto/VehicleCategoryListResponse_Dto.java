package com.packsendme.roadbrewa.vehicle.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadbrewa.dto.VehicleCategoryDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class VehicleCategoryListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<VehicleCategoryDto> vehiclesCategory = new ArrayList<VehicleCategoryDto>();

	public VehicleCategoryListResponse_Dto(List<VehicleCategoryDto> vehiclesCategory) {
		super();
		this.vehiclesCategory = vehiclesCategory;
	}

	public VehicleCategoryListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
