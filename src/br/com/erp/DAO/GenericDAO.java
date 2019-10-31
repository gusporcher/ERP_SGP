package br.com.erp.DAO;

import java.util.List;

public interface GenericDAO<T>{
	/**
	 * create record on database
	 * @param t record to be created
	 * @return created record
	 * @throws Exception
	 */
	public T insert(T t) throws Exception;
	
	/**
	 * update record on database
	 * @param t record to be updated
	 * @throws Exception
	 */
	public void update(T t) throws Exception;
	
	/**
	 * delete record on database
	 * @param id of the record to be deleted
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;
	
	/**
	 * get record from database based on its id
	 * @param id of the record to be return
	 * @return record from database
	 * @throws Exception
	 */
	public T getObject(int id) throws Exception;
	
	/**
	 * list all records from database
	 * @return list of all records found on database
	 * @throws Exception
	 */
	public List<T>readAll() throws Exception;
}
