package com.mjc.school.repository;

import com.mjc.school.repository.domain.Author;

public interface AuthorRepository extends Repository<Author> {
	boolean isPresent(final Long id);
}