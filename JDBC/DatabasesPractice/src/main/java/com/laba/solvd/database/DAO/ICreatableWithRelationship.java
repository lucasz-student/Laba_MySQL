package com.laba.solvd.database.DAO;

public interface ICreatableWithRelationship<S, U> {

	public void create(S t, U u);
}