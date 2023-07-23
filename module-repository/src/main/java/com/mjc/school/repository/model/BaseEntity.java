package com.mjc.school.repository.model;

import java.util.Objects;

public abstract class BaseEntity {

	private Long id;

	protected BaseEntity() {
		/* empty */
	}

	protected BaseEntity(final Long id) {
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
		final BaseEntity that = (BaseEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return 31;
	}
}