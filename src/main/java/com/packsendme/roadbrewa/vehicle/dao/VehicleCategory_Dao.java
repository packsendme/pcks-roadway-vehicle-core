package com.packsendme.roadbrewa.vehicle.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadbrewa.entity.VehicleCategory;
import com.packsendme.roadbrewa.vehicle.repository.IVehicleCategory_Repository;

@Component
@ComponentScan({"com.packsendme.roadbrewa.vehicle.repository"})
public class VehicleCategory_Dao implements ICrud_Dao<VehicleCategory>{

	@Autowired
	IVehicleCategory_Repository VehicleCategory_Rep; 
		
	@Override
	public VehicleCategory save(VehicleCategory entity) {
		try {
			return entity = VehicleCategory_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<VehicleCategory> findOneById(String id) {
		try {
			return VehicleCategory_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VehicleCategory> findAll() {
		try {
			List<VehicleCategory> entityL = new ArrayList<VehicleCategory>(); 
			entityL = VehicleCategory_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean remove(VehicleCategory entity) {
		try {
			VehicleCategory_Rep.delete(entity);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public VehicleCategory update(VehicleCategory entity) {
		try {
			VehicleCategory entityModel =  VehicleCategory_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public VehicleCategory findOneByName(String name) {
		try {
			return VehicleCategory_Rep.findVehicleByType(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public VehicleCategory findOneByIdAndName(String id, String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VehicleCategory> findEntityByParameters(String name) {
		try {
			return null;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

 
	
}