package com.mjc.school.repository.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.domain.AuthorModel;
import com.mjc.school.repository.utility.DataSource;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
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

			assertEquals(Collections.emptyList(), repository.readAll());
			verify(dataSource, times(1)).getAuthors();
		}

		@Test
		void getAll_shouldReturnTwoEntities_whenThereAreTwoNewsInTheStorage() {
			final List<AuthorModel> storage = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(dataSource.getAuthors()).thenReturn(storage);

			AuthorRepository repository = new AuthorRepositoryImpl(dataSource);

			assertEquals(storage, repository.readAll());
			verify(dataSource, times(1)).getAuthors();
		}
	}

	@Nested
	class TestIsPresent {

		@Test
		void isPresent_shouldReturnFalse_whenStorageIsEmpty() {
			when(dataSource.getAuthors()).thenReturn(new ArrayList<>());

			AuthorRepository repository = new AuthorRepositoryImpl(dataSource);

			assertFalse(repository.isPresent(99L));
		}

		@Test
		void isPresent_shouldReturnFalse_whenEntityIsNotFound() {
			final List<AuthorModel> storage = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(dataSource.getAuthors()).thenReturn(storage);

			AuthorRepository repository = new AuthorRepositoryImpl(dataSource);

			assertFalse(repository.isPresent(99L));
		}

		@Test
		void isPresent_shouldReturnTrue_whenEntityIsFound() {
			final List<AuthorModel> storage = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(dataSource.getAuthors()).thenReturn(storage);

			AuthorRepository repository = new AuthorRepositoryImpl(dataSource);

			assertTrue(repository.isPresent(2L));
		}
	}

	private AuthorModel createTestAuthor(Long authorId) {
		return new AuthorModel(
			authorId,
			"Author Name"
		);
	}
}