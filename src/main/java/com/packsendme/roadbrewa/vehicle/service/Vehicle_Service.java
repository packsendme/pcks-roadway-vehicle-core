package com.packsendme.roadbrewa.vehicle.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.roadbrewa.component.RoadwayManagerConstants;
import com.packsendme.roadbrewa.dto.VehicleDto;
import com.packsendme.roadbrewa.entity.Vehicle;
import com.packsendme.roadbrewa.vehicle.dao.Vehicle_Dao;
import com.packsendme.roadbrewa.vehicle.dto.VehicleListResponse_Dto;

@Service
@ComponentScan({"com.packsendme.roadbrewa.vehicle.dao"})
public class Vehicle_Service {
	
	@Autowired
	private Vehicle_Dao vehicle_Dao;
	
	private VehicleDto vehicleObj = new VehicleDto();

	public ResponseEntity<?> findAll() {
		Response<VehicleListResponse_Dto> responseObj = null;
		VehicleListResponse_Dto vehicleListResponse_Dto = new VehicleListResponse_Dto();
		try {
			vehicleListResponse_Dto.vehicles = vehicleObj.entityTOdto(vehicle_Dao.findAll());
			responseObj = new Response<VehicleListResponse_Dto>(0,HttpExceptionPackSend.CREATED_ROADWAYBRE.getAction(), vehicleListResponse_Dto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<VehicleListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> save(VehicleDto vehicleDto) {
		Response<VehicleDto> responseObj = null;
		try {
			if(vehicle_Dao.findOneByName(vehicleDto.vehicle_type) == null) {
				Vehicle entity = vehicleObj.dtoTOentity(vehicleDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
				entity = vehicle_Dao.save(entity);
				responseObj = new Response<VehicleDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleDto);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<VehicleDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> delete(String id) {
		Response<String> responseObj = null;
		try {
			Optional<Vehicle> vehicleData = vehicle_Dao.findOneById(id);
			if(vehicleData.isPresent()) {
				Vehicle entity = vehicleData.get();
				if(vehicle_Dao.remove(entity) == true) {
					responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_VEHICLE.getAction(), id);
					return new ResponseEntity<>(responseObj, HttpStatus.OK);
				}
				else {
					responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_VEHICLE.getAction(), id);
					return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_BODYWORK.getAction(), id);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> update(String id, VehicleDto vehicleDto) {
		Response<String> responseObj = null;
		try {
			// Check if exist same bodywork in Database
			Vehicle vehicleFind = vehicle_Dao.findOneByName(vehicleDto.vehicle_type);
			if(vehicleFind == null) {
				Optional<Vehicle> vehicleData = vehicle_Dao.findOneById(id);
				if(vehicleData.isPresent()) {
					Vehicle entity = vehicleObj.dtoTOentity(vehicleDto, vehicleData.get(), RoadwayManagerConstants.UPDATE_OP_ROADWAY);
					vehicle_Dao.update(entity);
					responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), id);
					return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
				}
				else {
					responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), null);
					return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
				}
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), id);
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
}
