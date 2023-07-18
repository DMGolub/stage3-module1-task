package com.mjc.school.repository;

import java.util.List;
import java.util.Optional;

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
	 * @return Optional<T> if entity is found and empty Optional otherwise.
	 */
	Optional<T> getById(final long id);

	/**
	 * Finds all entities in the data storage.
	 *
	 * @return List of entities found or an empty list.
	 */
	List<T> getAll();

	/**
	 * Updates provided entity in the data storage if the entity is found by id.
	 *
	 * @param t T entity to be updated.
	 * @return Optional<T> with updated entity of empty Optional if entity is not found.
	 */
	Optional<T> update(T t);

	/**
	 * Removes an entity from the data storage by given id.
	 *
	 * @param id long id of an entity to be removed.
	 * @return true if entity was removed and false otherwise.
	 */
	boolean delete(long id);
}