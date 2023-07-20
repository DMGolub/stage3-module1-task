package com.mjc.school.repository.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.domain.Author;
import com.mjc.school.repository.utility.DataSource;

import java.util.List;
import java.util.Optional;

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
	public Author save(final Author author) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public Optional<Author> getById(final long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public List<Author> getAll() {
		return dataSource.getAuthors();
	}

	@Override
	public Optional<Author> update(final Author author) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean delete(final long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean isPresent(final Long id) {
		return dataSource.getAuthors().stream().anyMatch(a -> a.getId().equals(id));
	}
}