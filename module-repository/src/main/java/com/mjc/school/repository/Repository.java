package com.mjc.school.repository;

import com.mjc.school.repository.exception.EntityNotFoundException;

import java.util.List;

public interface Repository<T> {

	/**
	 * Saves new entity to data storage.
	 *
	 * @param t T entity to be saved.
	 * @return T entity with updated id.
	 */
	T save(final T t);

	/**
	 * Finds an entity with provided id in the data storage.
	 *
	 * @param id long entity id, should be positive.
	 * @return T entity if it is found.
	 * @throws EntityNotFoundException if entity is not found.
	 */
	T getById(final long id) throws EntityNotFoundException;

	/**
	 * Finds all entities in the data storage.
	 *
	 * @return List of entities found or an empty list.
	 */
	List<T> getAll();

	/**
	 * Updates provided entity in the data storage.
	 *
	 * @param t T entity to be updated.
	 * @return updated entity.
	 * @throws EntityNotFoundException if entity is not found.
	 */
	T update(T t) throws EntityNotFoundException;

	/**
	 * Removes an entity from the data storage by given id.
	 *
	 * @param id long id of an entity to be removed.
	 * @return true if entity was removed and false otherwise.
	 */
	boolean delete(long id);
}