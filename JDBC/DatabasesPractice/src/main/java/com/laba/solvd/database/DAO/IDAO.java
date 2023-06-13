package com.laba.solvd.database.DAO;

import java.util.List;

public interface IDAO<T> {
	
	public static final String GET_BY_ID = "SELECT * FROM %s WHERE id=?";
	public static final String GET_ALL = "SELECT * FROM %s";
	
	public T selectById(int id);
	
	public List<T> selectAll();
	
	public void update(T t);
	
	public void delete(T t);
}