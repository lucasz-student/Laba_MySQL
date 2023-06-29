package com.laba.solvd.database.DAO;

import java.util.List;

public interface IDAO<T> {

	public void create(T t);
	public T selectById(int id);
	public List<T> selectAll();
	public void update(T t);
	public void delete(T t);
}
