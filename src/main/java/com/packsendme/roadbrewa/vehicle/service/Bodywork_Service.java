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
import com.packsendme.roadbrewa.dto.BodyworkDto;
import com.packsendme.roadbrewa.entity.Bodywork;
import com.packsendme.roadbrewa.vehicle.dao.Bodywork_Dao;
import com.packsendme.roadbrewa.vehicle.dto.BodyworkListResponse_Dto;

@Service
@ComponentScan({"com.packsendme.roadbrewa.vehicle.dao"})
public class Bodywork_Service {
	
	@Autowired
	private Bodywork_Dao bodywork_Dao;

	
	private BodyworkDto bodyworkObj_Dto = new BodyworkDto(); 

	public ResponseEntity<?> findAll() {
		Response<BodyworkListResponse_Dto> responseObj = null;
		BodyworkListResponse_Dto bodyworkListResponse_Dto = new BodyworkListResponse_Dto();
		try {
			bodyworkListResponse_Dto.bodies = bodyworkObj_Dto.entityTOdto(bodywork_Dao.findAll());
			responseObj = new Response<BodyworkListResponse_Dto>(0,HttpExceptionPackSend.CREATED_BODYWORK.getAction(), bodyworkListResponse_Dto);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<BodyworkListResponse_Dto>(0,HttpExceptionPackSend.CREATED_BODYWORK.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> save(BodyworkDto bodyworkDto) {
		Response<BodyworkDto> responseObj = null;
		try {
			if(bodywork_Dao.findOneByName(bodyworkDto.bodyWork) == null) {
				Bodywork entity = bodyworkObj_Dto.dtoTOentity(bodyworkDto, null, RoadwayManagerConstants.ADD_OP_ROADWAY);
				entity = bodywork_Dao.save(entity);
				responseObj = new Response<BodyworkDto>(0,HttpExceptionPackSend.CREATED_BODYWORK.getAction(), bodyworkDto);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<BodyworkDto>(0,HttpExceptionPackSend.CREATED_BODYWORK.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> delete(String id) {
		Response<Bodywork> responseObj = null;
		try {
			Optional<Bodywork> bodyWorkData = bodywork_Dao.findOneById(id);
			if(bodyWorkData.isPresent()) {
				Bodywork bodyWorkEntity = bodyWorkData.get();
				if(bodywork_Dao.remove(bodyWorkEntity) == true) {
					responseObj = new Response<Bodywork>(0,HttpExceptionPackSend.DELETE_BODYWORK.getAction(), bodyWorkData.get());
					return new ResponseEntity<>(responseObj, HttpStatus.OK);
				}
				else {
					responseObj = new Response<Bodywork>(0,HttpExceptionPackSend.DELETE_BODYWORK.getAction(), bodyWorkData.get());
					return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
				}
			}
			else {
				responseObj = new Response<Bodywork>(0,HttpExceptionPackSend.DELETE_BODYWORK.getAction(), bodyWorkData.get());
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<Bodywork>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> prepareUpdate(String id, BodyworkDto bodyworkDto) {
		Response<String> responseObj = null;
		try {
			// Check if exist same bodywork in Database
			Bodywork bodyworkFindName = bodywork_Dao.findOneByName(bodyworkDto.bodyWork);
			
			if(bodyworkFindName == null) {
				return update(id, bodyworkDto);
			}
			else if((bodyworkFindName != null) && (bodyworkFindName.id.equals(id))) {
				return update(id, bodyworkDto);
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_BODYWORK.getAction(), id);
				return new ResponseEntity<>(responseObj, HttpStatus.FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_BODYWORK.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}

	
	public ResponseEntity<?> update(String id, BodyworkDto bodyworkDto) {
		Response<String> responseObj = null;
		try {
			Optional<Bodywork> bodyWorkData = bodywork_Dao.findOneById(id);
			if(bodyWorkData.isPresent()) {
				Bodywork entity = bodyworkObj_Dto.dtoTOentity(bodyworkDto, bodyWorkData.get(), RoadwayManagerConstants.UPDATE_OP_ROADWAY);
				entity = bodywork_Dao.update(entity);
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_BODYWORK.getAction(), entity.id);
				return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
			}
			else {
				responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_BODYWORK.getAction(), null);
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			responseObj = new Response<String>(0,HttpExceptionPackSend.UPDATE_BODYWORK.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}

}
