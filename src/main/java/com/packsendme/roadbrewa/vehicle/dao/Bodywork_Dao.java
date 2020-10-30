package com.packsendme.roadbrewa.vehicle.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClientException;
import com.packsendme.roadbrewa.entity.Bodywork;
import com.packsendme.roadbrewa.vehicle.repository.IBodywork_Repository;

@Component
@ComponentScan({"com.packsendme.roadbrewa.vehicle.repository"})
public class Bodywork_Dao implements ICrud_Dao<Bodywork> {

	@Autowired
	IBodywork_Repository bodywork_Rep; 
	
	
	@Override
	public Bodywork save(Bodywork entity) {
		try {
			return entity = bodywork_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<Bodywork> findOneById(String id) {
		try {
			return bodywork_Rep.findById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bodywork> findAll() {
		try {
			List<Bodywork> entityL = new ArrayList<Bodywork>(); 
			entityL = bodywork_Rep.findAll();
			return entityL;
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean remove(Bodywork entity) {
		try {
			bodywork_Rep.delete(entity);
			return true; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return false; 
		}		
	}

	@Override
	public Bodywork update(Bodywork entity) {
		try {
			Bodywork entityModel = bodywork_Rep.save(entity);
			return entityModel; 
		}
		catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	@Override
	public Bodywork findOneByName(String name) {
		try {
			return bodywork_Rep.findBodyWorkByName(name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Bodywork findOneByIdAndName(String id, String name) {
		try {
			return bodywork_Rep.findBodyWorkByIdAndName(id, name);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bodywork> findEntityByParameters(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
 
}
