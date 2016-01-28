package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;

public interface GenericDAO <PK, T> {
	
	public int insert(T entity) throws SQLExceptionIFOpenDoors;

	public void update(T entity) throws SQLExceptionIFOpenDoors;

	public int delete(PK pk) throws SQLExceptionIFOpenDoors;

	public List<T> getAll() throws SQLExceptionIFOpenDoors;
	
	public List<T> getAll(String namedQuery) throws SQLExceptionIFOpenDoors;

	public T getById(PK pk) throws SQLExceptionIFOpenDoors;

	public List<T> find(T entity) throws SQLExceptionIFOpenDoors;

	//public List<T> convertToList(ResultSet rs) throws SQLExceptionIFOpenDoors;
}
