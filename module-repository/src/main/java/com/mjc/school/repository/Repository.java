package com.mjc.school.repository;

import java.util.List;

public interface Repository<T> {

	/**
	 * Saves new entity to data storage.
	 *
	 * @param t T entity to be saved.
	 * @return T entity with updated id.
	 */
	T create(final T t);

	/**
	 * Finds an entity with provided id in the data storage.
	 *
	 * @param id long entity id, should be positive.
	 * @return T if entity is found and null otherwise.
	 */
	T readById(final Long id);

	/**
	 * Finds all entities in the data storage.
	 *
	 * @return List of entities found or an empty list.
	 */
	List<T> readAll();

	/**
	 * Updates provided entity in the data storage if the entity is found by id.
	 *
	 * @param t T entity to be updated.
	 * @return T with updated entity of null if entity is not found.
	 */
	T update(final T t);

	/**
	 * Removes an entity from the data storage by given id.
	 *
	 * @param id long id of an entity to be removed.
	 * @return true if entity was removed and false otherwise.
	 */
	Boolean delete(final Long id);
}