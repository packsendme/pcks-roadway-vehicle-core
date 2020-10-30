package com.packsendme.roadbrewa.vehicle.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadbrewa.entity.Vehicle;
import com.packsendme.roadbrewa.vehicle.repository.IVehicle_Repository;

@Component
@ComponentScan({"com.packsendme.roadbrewa.vehicle.repository"})
public class Vehicle_Dao implements ICrud_Dao<Vehicle> {

	@Autowired
	IVehicle_Repository vehicle_Rep; 

	@Override
	public Vehicle save(Vehicle entity) {
		try {
			return entity = vehicle_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<Vehicle> findOneById(String id) {
		try {
			return vehicle_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Vehicle> findAll() {
		try {
			List<Vehicle> entityL = new ArrayList<Vehicle>(); 
			entityL = vehicle_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean remove(Vehicle entity) {
		try {
			vehicle_Rep.delete(entity);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public Vehicle update(Vehicle entity) {
		try {
			Vehicle entityModel =  vehicle_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	@Override
	public Vehicle findOneByName(String name) {
		try {
			return vehicle_Rep.findVehicleByName(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Vehicle findOneByIdAndName(String id, String name) {
		try {
			return vehicle_Rep.findVehicleByIdAndName(id, name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Vehicle> findEntityByParameters(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
