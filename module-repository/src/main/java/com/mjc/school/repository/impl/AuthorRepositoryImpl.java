package com.mjc.school.repository.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.domain.AuthorModel;
import com.mjc.school.repository.utility.DataSource;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

	private static final String UNSUPPORTED_MESSAGE = "Operation is not implemented yet";
	private final DataSource dataSource;

	public AuthorRepositoryImpl() {
		dataSource = DataSource.getInstance();
	}

	public AuthorRepositoryImpl(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public AuthorModel create(final AuthorModel author) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public AuthorModel readById(final long id) {
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
	public Boolean delete(final long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean isPresent(final Long id) {
		return dataSource.getAuthors().stream().anyMatch(a -> a.getId().equals(id));
	}
}