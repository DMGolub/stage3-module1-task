package com.mjc.school.repository;

import com.mjc.school.repository.domain.AuthorModel;

public interface AuthorRepository extends Repository<AuthorModel> {
	boolean isPresent(final Long id);
}