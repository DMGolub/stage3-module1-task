package com.mjc.school.repository.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.domain.Author;
import com.mjc.school.repository.utility.DataSource;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthorRepositoryImplTest {

	private final DataSource dataSource = Mockito.mock(DataSource.class);

	@Nested
	class TestGetAll {

		@Test
		void getAll_shouldReturnEmptyList_whenStorageIsEmpty() {
			AuthorRepository repository = new AuthorRepositoryImpl(dataSource);
			when(dataSource.getAuthors()).thenReturn(Collections.emptyList());

			assertEquals(Collections.emptyList(), repository.getAll());
			verify(dataSource, times(1)).getAuthors();
		}

		@Test
		void getAll_shouldReturnTwoEntities_whenThereAreTwoNewsInTheStorage() {
			final List<Author> storage = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(dataSource.getAuthors()).thenReturn(storage);

			AuthorRepository repository = new AuthorRepositoryImpl(dataSource);

			assertEquals(storage, repository.getAll());
			verify(dataSource, times(1)).getAuthors();
		}
	}

	private Author createTestAuthor(Long authorId) {
		return new Author(
			authorId,
			"Author Name"
		);
	}
}