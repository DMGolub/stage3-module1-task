package com.mjc.school.repository.domain;

import java.util.Objects;

public class BaseModel {

	private Long id;

	public BaseModel() {
		/* empty */
	}

	public BaseModel(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BaseEntity{id=" + id + "}";
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final BaseModel that = (BaseModel) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return 31;
	}
}