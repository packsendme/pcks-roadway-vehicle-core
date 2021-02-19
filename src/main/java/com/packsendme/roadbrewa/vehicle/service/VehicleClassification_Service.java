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
import com.packsendme.roadbrewa.dto.VehicleClassificationDto;
import com.packsendme.roadbrewa.entity.VehicleClassification;
import com.packsendme.roadbrewa.vehicle.dao.VehicleClassification_Dao;
import com.packsendme.roadbrewa.vehicle.dto.VehicleClassificationListResponse_Dto;

@Service
@ComponentScan({"com.packsendme.roadbrewa.vehicle.dao"})
public class VehicleClassification_Service {

	@Autowired
	private VehicleClassification_Dao vehicleCategory_Dao;
	
	private VehicleClassificationDto vehicleClassificationObj = new VehicleClassificationDto();

	
	public ResponseEntity<?> findAll() {
		Response<VehicleClassificationListResponse_Dto> responseObj = null;
		VehicleClassificationListResponse_Dto vehicleResponse_Dto = new  VehicleClassificationListResponse_Dto();
		try {
			vehicleResponse_Dto.vehiclesCategory = vehicleClassificationObj.entityTOdto(vehicleCategory_Dao.findAll());
			responseObj = new Response<VehicleClassificationListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleResponse_Dto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<VehicleClassificationListResponse_Dto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> findVehicleByType(String vehicle_type) {
		Response<VehicleClassificationDto> responseObj = null;
		try {
			VehicleClassificationDto vehicleCategoryDto  = vehicleClassificationObj.entityTOdtoObj(vehicleCategory_Dao.findOneByName(vehicle_type));
			responseObj = new Response<VehicleClassificationDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleCategoryDto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<VehicleClassificationDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> save(VehicleClassificationDto vehicleCategoryDto) {
		Response<VehicleClassificationDto> responseObj = null;
		try {
			if(vehicleCategory_Dao.findOneByName(vehicleCategoryDto.type_vehicle) == null) {
				VehicleClassification entity = vehicleClassificationObj.dtoTOentity(vehicleCategoryDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
				vehicleCategory_Dao.save(entity);
				responseObj = new Response<VehicleClassificationDto>(0,HttpExceptionPackSend.CREATED_VEHICLE.getAction(), vehicleCategoryDto);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<VehicleClassificationDto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> delete(String id) {
		Response<String> responseObj = null;
		responseObj = new Response<String>(0,HttpExceptionPackSend.DELETE_VEHICLE.getAction(), null);

		try {
			Optional<VehicleClassification> vehicleData = vehicleCategory_Dao.findOneById(id);
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
	
	public ResponseEntity<?> update(String id, VehicleClassificationDto vehicleCategoryDto) {
		Response<String> responseObj = null;
		try {
			// Check if exist same bodywork in Database
			VehicleClassification vehicleFind = vehicleCategory_Dao.findOneByName(vehicleCategoryDto.type_vehicle);
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
	
	public ResponseEntity<?> executeUpdate(String id, VehicleClassificationDto vehicleCategoryDto) {
		Response<String> responseObj = null;

		try {
			Optional<VehicleClassification> vehicleCategoryData = vehicleCategory_Dao.findOneById(id);
			if(vehicleCategoryData.isPresent()) {
				VehicleClassification entity = vehicleClassificationObj.dtoTOentity(vehicleCategoryDto, vehicleCategoryData.get(), RoadwayManagerConstants.UPDATE_OP_ROADWAY);
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
