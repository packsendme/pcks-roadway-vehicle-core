package com.packsendme.roadway.vehicle.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadway.commons.entity.VehicleType;
import com.packsendme.roadway.vehicle.repository.IVehicleType_Repository;

@Component
@ComponentScan({"com.packsendme.roadbrewa.vehicle.repository"})
public class VehicleType_Dao implements ICrud_Dao<VehicleType>{

	@Autowired
	IVehicleType_Repository vehicleType_Rep; 
		
	@Override
	public VehicleType save(VehicleType entity) {
		try {
			return entity = vehicleType_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<VehicleType> findOneById(String id) {
		try {
			return vehicleType_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VehicleType> findAll() {
		try {
			List<VehicleType> entityL = new ArrayList<VehicleType>(); 
			entityL = vehicleType_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean remove(VehicleType entity) {
		try {
			vehicleType_Rep.delete(entity);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public VehicleType update(VehicleType entity) {
		try {
			VehicleType entityModel =  vehicleType_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public VehicleType findOneByName(String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VehicleType findOneByIdAndName(String id, String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VehicleType> findEntityByParameters(String name) {
		try {
			return vehicleType_Rep.findVehicleTypeByType(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

 
	
}