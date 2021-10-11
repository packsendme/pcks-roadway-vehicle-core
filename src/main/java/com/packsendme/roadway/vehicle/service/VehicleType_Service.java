package com.packsendme.roadway.vehicle.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.cross.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.cross.common.response.Response;
import com.packsendme.roadway.commons.constants.RoadwayManagerConstants;
import com.packsendme.roadway.commons.dto.VehicleTypeDto;
import com.packsendme.roadway.commons.entity.VehicleType;
import com.packsendme.roadway.vehicle.dao.VehicleType_Dao;
import com.packsendme.roadway.vehicle.dto.VehicleTypeListResponse_Dto;

@Service
@ComponentScan({"com.packsendme.roadway.vehicle.dao"})
public class VehicleType_Service {

	@Autowired
	private VehicleType_Dao vehicleType_Dao;
	
	private VehicleTypeDto vehicleTypeObj = new VehicleTypeDto();

	
	public ResponseEntity<?> findAll() {
		Response<VehicleTypeListResponse_Dto> responseObj = null;
		VehicleTypeListResponse_Dto vehicleTypeListResponse_Dto = new  VehicleTypeListResponse_Dto();
		try {
			vehicleTypeListResponse_Dto.vehiclesType = vehicleTypeObj.entityTOdto(vehicleType_Dao.findAll());
			responseObj = new Response<VehicleTypeListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleTypeListResponse_Dto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<VehicleTypeListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public ResponseEntity<?> save(VehicleTypeDto vehicleTypeDto) {
		Response<VehicleTypeDto> responseObj = null;
		try {
			if(vehicleType_Dao.findOneByName(vehicleTypeDto.type_vehicle) == null) {
				VehicleType entity = vehicleTypeObj.dtoTOentity(vehicleTypeDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
				vehicleType_Dao.save(entity);
				responseObj = new Response<VehicleTypeDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleTypeDto);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<VehicleTypeDto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> delete(String id) {
		Response<String> responseObj = null;
		responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_VEHICLE.getAction(), null);

		try {
			Optional<VehicleType> vehicleData = vehicleType_Dao.findOneById(id);
			if (vehicleData.isPresent()) {
				if(vehicleType_Dao.remove(vehicleData.get()) == true) {
					return new ResponseEntity<>(responseObj, HttpStatus.OK);
				}
				else{
					return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> update(String id, VehicleTypeDto vehicleTypeDto) {
		Response<String> responseObj = null;
		try {
			// Check if exist same bodywork in Database
			VehicleType vehicleFind = vehicleType_Dao.findOneByName(vehicleTypeDto.type_vehicle);
			if(vehicleFind == null) {
				return executeUpdate(id, vehicleTypeDto);
			}
			else if((vehicleFind != null) && (vehicleFind.type_vehicle.equals(vehicleTypeDto.type_vehicle))) {
				return executeUpdate(id, vehicleTypeDto);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
	public ResponseEntity<?> executeUpdate(String id, VehicleTypeDto vehicleTypeDto) {
		Response<String> responseObj = null;

		try {
			Optional<VehicleType> vehicleTypeData = vehicleType_Dao.findOneById(id);
			if(vehicleTypeData.isPresent()) {
				VehicleType entity = vehicleTypeObj.dtoTOentity(vehicleTypeDto, vehicleTypeData.get(), RoadwayManagerConstants.UPDATE_OP_ROADWAY);
				vehicleType_Dao.update(entity);
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), id);
				return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), null);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}

	
	
}
