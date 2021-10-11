package com.packsendme.roadway.vehicle.dao;

import java.util.List;
import java.util.Optional;

public interface ICrud_Dao<T> {

	public T save(T entity);

	public Optional<T> findOneById(String id);
	
	public List<T> findAll();
	
	public Boolean remove(T entity);
	
	public T update(T entity);
	
	public T findOneByIdAndName(String id, String name);

	public T findOneByName(String name);
	
	public List<T> findEntityByParameters(String name);

}
