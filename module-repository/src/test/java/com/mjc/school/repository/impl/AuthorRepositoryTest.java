package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.utility.DataSource;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthorRepositoryTest {

	private final DataSource dataSource = Mockito.mock(DataSource.class);
	private final Repository<AuthorModel> repository = new AuthorRepository(dataSource);

	@Nested
	class TestReadAll {

		@Test
		void readAll_shouldReturnEmptyList_whenStorageIsEmpty() {
			when(dataSource.getAuthors()).thenReturn(Collections.emptyList());

			assertEquals(Collections.emptyList(), repository.readAll());
			verify(dataSource, times(1)).getAuthors();
		}

		@Test
		void readAll_shouldReturnTwoEntities_whenThereAreTwoNewsInTheStorage() {
			final List<AuthorModel> storage = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(dataSource.getAuthors()).thenReturn(storage);

			assertEquals(storage, repository.readAll());
			verify(dataSource, times(1)).getAuthors();
		}
	}

	@Nested
	class TestIsExistById {

		@Test
		void isExistById_shouldReturnFalse_whenStorageIsEmpty() {
			when(dataSource.getAuthors()).thenReturn(new ArrayList<>());

			assertFalse(repository.isExistById(99L));
		}

		@Test
		void isExistById_shouldReturnFalse_whenEntityIsNotFound() {
			final List<AuthorModel> storage = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(dataSource.getAuthors()).thenReturn(storage);

			assertFalse(repository.isExistById(99L));
		}

		@Test
		void isExistById_shouldReturnTrue_whenEntityIsFound() {
			final List<AuthorModel> storage = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(dataSource.getAuthors()).thenReturn(storage);

			assertTrue(repository.isExistById(2L));
		}
	}

	private AuthorModel createTestAuthor(Long authorId) {
		return new AuthorModel(
			authorId,
			"Author Name"
		);
	}
}