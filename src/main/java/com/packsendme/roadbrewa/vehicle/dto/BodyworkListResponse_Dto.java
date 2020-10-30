package com.packsendme.roadbrewa.vehicle.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadbrewa.dto.BodyworkDto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class BodyworkListResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<BodyworkDto> bodies = new ArrayList<BodyworkDto>();

	
	public BodyworkListResponse_Dto(List<BodyworkDto> bodies) {
		super();
		this.bodies = bodies;
	}

	public BodyworkListResponse_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
