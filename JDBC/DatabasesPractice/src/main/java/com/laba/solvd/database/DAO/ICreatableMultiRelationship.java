package com.laba.solvd.database.DAO;

public interface ICreatableMultiRelationship<A, B, C> {

	public void create(A a, B b, C c);
}