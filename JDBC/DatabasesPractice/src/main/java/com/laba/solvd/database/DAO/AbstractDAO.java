package com.laba.solvd.database.DAO;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class AbstractDAO<T> {
	
	protected Connection connection; 
	public static final String GET_BY_ID = "SELECT * FROM %s WHERE id=?";
	public static final String GET_ALL = "SELECT * FROM %s";
	
	protected AbstractDAO() throws InterruptedException, ExecutionException {
		this.connection = (Connection) ConnectionManager.getSQLConnection().get();
	}
	
	public abstract T selectById(int id);
	public abstract List<T> selectAll();
	public abstract void update(T t);
	public abstract void delete(T t);
}
