package com.mjc.school.service;

import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;

import java.util.List;

public interface Service<T, R> {

	/**
	 * Saves request DTO converting it to entity.
	 *
	 * @param t T entity to be saved.
	 * @return R entity DTO with updated id.
	 * @throws ValidationException if there is any constraint violation.
	 */
	R save(final T t) throws ValidationException;

	/**
	 * Finds an entity with provided id and converts to DTO.
	 *
	 * @param id long entity id, should be positive.
	 * @return R entity DTO if it is found.
	 * @throws EntityNotFoundException if entity is not found by id,
	 * 		ValidationException if id violates positive constraint.
	 */
	R getById(final long id) throws EntityNotFoundException, ValidationException;

	/**
	 * Finds all entities.
	 *
	 * @return List of entity DTO found or an empty list.
	 */
	List<R> getAll();

	/**
	 * Updates entity for provided DTO.
	 *
	 * @param t T entity to be updated.
	 * @return updated entity DTO.
	 * @throws EntityNotFoundException if entity is not found by id,
	 * 		ValidationException if there is any constraint violation.
	 */
	R update(T t) throws EntityNotFoundException, ValidationException;

	/**
	 * Removes an entity by given id.
	 *
	 * @param id long id of an entity to be removed.
	 * @return true if entity was removed and false otherwise.
	 * @throws ValidationException if id violates positive constraint.
	 */
	boolean delete(long id) throws ValidationException;
}