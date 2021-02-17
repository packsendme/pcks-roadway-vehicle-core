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
import com.packsendme.roadbrewa.dto.VehicleCategoryDto;
import com.packsendme.roadbrewa.entity.VehicleCategory;
import com.packsendme.roadbrewa.vehicle.dao.VehicleCategory_Dao;
import com.packsendme.roadbrewa.vehicle.dto.VehicleCategoryListResponse_Dto;
import com.packsendme.roadbrewa.vehicle.dto.VehicleTypeListResponse_Dto;

@Service
@ComponentScan({"com.packsendme.roadbrewa.vehicle.dao"})
public class VehicleCategory_Service {

	@Autowired
	private VehicleCategory_Dao vehicleCategory_Dao;
	
	private VehicleCategoryDto vehicleCategoryObj = new VehicleCategoryDto();

	
	public ResponseEntity<?> findAll() {
		Response<VehicleCategoryListResponse_Dto> responseObj = null;
		VehicleCategoryListResponse_Dto vehicleResponse_Dto = new  VehicleCategoryListResponse_Dto();
		try {
			vehicleResponse_Dto.vehiclesCategory = vehicleCategoryObj.entityTOdto(vehicleCategory_Dao.findAll());
			responseObj = new Response<VehicleCategoryListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleResponse_Dto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<VehicleCategoryListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> findVehicleByType(String vehicle_type) {
		Response<VehicleCategoryDto> responseObj = null;
		try {
			VehicleCategoryDto vehicleCategoryDto  = vehicleCategoryObj.entityTOdtoObj(vehicleCategory_Dao.findOneByName(vehicle_type));
			responseObj = new Response<VehicleCategoryDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleCategoryDto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<VehicleCategoryDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> save(VehicleCategoryDto vehicleCategoryDto) {
		Response<VehicleCategoryDto> responseObj = null;
		try {
			if(vehicleCategory_Dao.findOneByName(vehicleCategoryDto.type_vehicle) == null) {
				VehicleCategory entity = vehicleCategoryObj.dtoTOentity(vehicleCategoryDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
				vehicleCategory_Dao.save(entity);
				responseObj = new Response<VehicleCategoryDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleCategoryDto);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<VehicleCategoryDto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> delete(String id) {
		Response<String> responseObj = null;
		responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_VEHICLE.getAction(), null);

		try {
			Optional<VehicleCategory> vehicleData = vehicleCategory_Dao.findOneById(id);
			if (vehicleData.isPresent()) {
				if(vehicleCategory_Dao.remove(vehicleData.get()) == true) {
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
	
	public ResponseEntity<?> update(String id, VehicleCategoryDto vehicleCategoryDto) {
		Response<String> responseObj = null;
		try {
			// Check if exist same bodywork in Database
			VehicleCategory vehicleFind = vehicleCategory_Dao.findOneByName(vehicleCategoryDto.type_vehicle);
			if(vehicleFind == null) {
				return executeUpdate(id, vehicleCategoryDto);
			}
			else if((vehicleFind != null) && (vehicleFind.type_vehicle.equals(vehicleCategoryDto.type_vehicle))) {
				return executeUpdate(id, vehicleCategoryDto);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
	public ResponseEntity<?> executeUpdate(String id, VehicleCategoryDto vehicleCategoryDto) {
		Response<String> responseObj = null;

		try {
			Optional<VehicleCategory> vehicleCategoryData = vehicleCategory_Dao.findOneById(id);
			if(vehicleCategoryData.isPresent()) {
				VehicleCategory entity = vehicleCategoryObj.dtoTOentity(vehicleCategoryDto, vehicleCategoryData.get(), RoadwayManagerConstants.UPDATE_OP_ROADWAY);
				vehicleCategory_Dao.update(entity);
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
