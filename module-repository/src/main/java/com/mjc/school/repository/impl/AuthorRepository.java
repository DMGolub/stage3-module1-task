package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.utility.DataSource;

import java.util.List;

public class AuthorRepository implements Repository<AuthorModel> {

	private static final String UNSUPPORTED_MESSAGE = "Operation is not implemented yet";
	private final DataSource dataSource;

	public AuthorRepository() {
		dataSource = DataSource.getInstance();
	}

	AuthorRepository(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public AuthorModel create(final AuthorModel author) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public AuthorModel readById(final Long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public List<AuthorModel> readAll() {
		return dataSource.getAuthors();
	}

	@Override
	public AuthorModel update(final AuthorModel author) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public Boolean deleteById(final Long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public Boolean isExistById(final Long id) {
		return dataSource.getAuthors().stream().anyMatch(a -> a.getId().equals(id));
	}
}