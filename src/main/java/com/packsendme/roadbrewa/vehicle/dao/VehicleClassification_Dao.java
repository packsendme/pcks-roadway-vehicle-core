package com.packsendme.roadbrewa.vehicle.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadbrewa.entity.VehicleClassification;
import com.packsendme.roadbrewa.vehicle.repository.IVehicleClassification_Repository;

@Component
@ComponentScan({"com.packsendme.roadbrewa.vehicle.repository"})
public class VehicleClassification_Dao implements ICrud_Dao<VehicleClassification>{

	@Autowired
	IVehicleClassification_Repository vehicleClassification_Rep; 
		
	@Override
	public VehicleClassification save(VehicleClassification entity) {
		try {
			return entity = vehicleClassification_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<VehicleClassification> findOneById(String id) {
		try {
			return vehicleClassification_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VehicleClassification> findAll() {
		try {
			List<VehicleClassification> entityL = new ArrayList<VehicleClassification>(); 
			entityL = vehicleClassification_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean remove(VehicleClassification entity) {
		try {
			vehicleClassification_Rep.delete(entity);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public VehicleClassification update(VehicleClassification entity) {
		try {
			VehicleClassification entityModel = vehicleClassification_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public VehicleClassification findOneByName(String name) {
		try {
			return vehicleClassification_Rep.findVehicleByType(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VehicleClassification findOneByIdAndName(String id, String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VehicleClassification> findEntityByParameters(String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

 
	
}