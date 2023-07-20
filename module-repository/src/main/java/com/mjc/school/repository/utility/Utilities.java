package com.mjc.school.repository.utility;

import com.mjc.school.repository.domain.BaseModel;

import java.util.Comparator;
import java.util.List;

public class Utilities {

	private Utilities() {
		// Empty. Hiding default public constructor
	}

	/**
	 * Finds index of entity with given id in the provided storage.
	 *
	 * @param storage List of entities.
	 * @param id long id of an entity to be found.
	 * @return int entity index if found and -1 otherwise.
	 * @param <T> entity class extending BaseEntity.
	 */
	public static <T extends BaseModel> int getIndexById(final List<T> storage, final long id) {
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i) != null && storage.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Finds maximum entity id value in the given storage.
	 *
	 * @param storage List of entities.
	 * @return long maximum id if found and 0 otherwise.
	 * @param <T> entity class extending BaseEntity.
	 */
	public static <T extends BaseModel> long getMaxId(final List<T> storage) {
		return storage.stream()
			.map(BaseModel::getId)
			.max(Comparator.naturalOrder())
			.orElse(0L);
	}
}