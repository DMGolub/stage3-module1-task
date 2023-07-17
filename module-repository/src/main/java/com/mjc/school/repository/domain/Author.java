package com.mjc.school.repository.domain;

import java.util.Objects;

public class Author extends BaseEntity {

	private String name;

	public Author(final Long id, final String name) {
		super(id);
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Author{id=" + getId() +
			", name='" + name + '\'' +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Author author = (Author) o;
		if (!Objects.equals(getId(), author.getId())) {
			return false;
		}
		return Objects.equals(name, author.name);
	}

	@Override
	public int hashCode() {
		return 17;
	}
}