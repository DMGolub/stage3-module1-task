package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utility.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NewsRepositoryTest {

	private final DataSource dataSource = Mockito.mock(DataSource.class);
	private final Repository<NewsModel> repository = new NewsRepository(dataSource);

	@Nested
	class TestCreate {

		@Test
		void create_shouldSaveNewsAndReturnNewsWithId1_whenStorageIsEmpty() {
			final List<NewsModel> storage = new ArrayList<>();
			when(dataSource.getNews()).thenReturn(storage);

			NewsModel news = createTestNews(null);
			NewsModel expected = createTestNews(1L);

			assertEquals(expected, repository.create(news));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void create_shouldSaveNewsAndReturnNewsWithId3_whenStorageContainsTwoNews() {
			final List<NewsModel> storage = new ArrayList<>();
			storage.add(createTestNews(1L));
			storage.add(createTestNews(2L));
			when(dataSource.getNews()).thenReturn(storage);

			NewsModel news = createTestNews(null);
			NewsModel expected = createTestNews(3L);

			assertEquals(expected, repository.create(news));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void create_shouldSaveNewsAndReturnNewsWithId4_whenStorageContainsTwoNewsWithId12And5() {
			final List<NewsModel> storage = new ArrayList<>();
			storage.add(createTestNews(12L));
			storage.add(createTestNews(15L));
			when(dataSource.getNews()).thenReturn(storage);

			NewsModel news = createTestNews(null);
			NewsModel expected = createTestNews(16L);

			assertEquals(expected, repository.create(news));
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestReadById {

		@Test
		void readById_shouldThrowNoSuchElementException_whenNewsNotFound() {
			final List<NewsModel> storage = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(dataSource.getNews()).thenReturn(storage);

			assertThrows(NoSuchElementException.class, () -> repository.readById(3L));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void readById_shouldReturnEntity_whenNewsWithGivenIdExists() {
			final long id = 2L;
			final NewsModel expected = createTestNews(id);
			final List<NewsModel> storage = Arrays.asList(createTestNews(1L), expected);
			when(dataSource.getNews()).thenReturn(storage);

			assertEquals(expected, repository.readById(id));
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestReadAll {

		@Test
		void readAll_shouldReturnEmptyList_whenStorageIsEmpty() {
			when(dataSource.getNews()).thenReturn(Collections.emptyList());

			assertEquals(Collections.emptyList(), repository.readAll());
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void readAll_shouldReturnTwoEntities_whenThereAreTwoNewsInTheStorage() {
			final List<NewsModel> storage = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(dataSource.getNews()).thenReturn(storage);

			assertEquals(storage, repository.readAll());
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestUpdate {

		@Test
		void update_shouldThrowNoSuchElementException_whenNewsWithGivenIdNotFound() {
			final List<NewsModel> storage = new ArrayList<>();
			storage.add(createTestNews(1L));
			storage.add(createTestNews(2L));
			when(dataSource.getNews()).thenReturn(storage);
			NewsModel updated = createTestNews(99L);

			assertThrows(NoSuchElementException.class, () -> repository.update(updated));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void update_shouldReturnUpdatedEntity_whenEntityIsValid() {
			final List<NewsModel> storage = new ArrayList<>();
			storage.add(createTestNews(1L));
			storage.add(createTestNews(2L));
			when(dataSource.getNews()).thenReturn(storage);

			final NewsModel updated = createTestNews(2L);
			updated.setTitle("Updated title");
			updated.setContent("Updated content");
			updated.setAuthorId(2L);
			List<NewsModel> expected = Arrays.asList(createTestNews(1L), updated);

			assertEquals(updated, repository.update(updated));
			assertEquals(expected, storage);
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestDeleteById {

		@Test
		void deleteById_shouldReturnFalse_whenThereIsNoNewsWithGivenId() {
			final List<NewsModel> storage = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(dataSource.getNews()).thenReturn(storage);

			assertFalse(repository.deleteById(99L));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void deleteById_shouldReturnFalse_whenStorageIsEmpty() {
			when(dataSource.getNews()).thenReturn(Collections.emptyList());

			assertFalse(repository.deleteById(99L));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void deleteById_shouldReturnTrue_whenNewsWithGivenIdDeleted() {
			final List<NewsModel> storage = new ArrayList<>();
			storage.add(createTestNews(1L));
			storage.add(createTestNews(2L));
			storage.add(createTestNews(3L));
			storage.add(createTestNews(4L));
			storage.add(createTestNews(5L));
			when(dataSource.getNews()).thenReturn(storage);

			assertTrue(repository.deleteById(3L));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void deleteById_shouldDeleteEntity_whenItIsSingleEntityInTheStorage() {
			final List<NewsModel> storage = new ArrayList<>();
			storage.add(createTestNews(3L));
			when(dataSource.getNews()).thenReturn(storage);

			assertTrue(repository.deleteById(3L));
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestIsExistById {

		@Test
		void isExistById_shouldReturnFalse_whenStorageIsEmpty() {
			when(dataSource.getNews()).thenReturn(new ArrayList<>());

			assertFalse(repository.isExistById(99L));
		}

		@Test
		void isExistById_shouldReturnFalse_whenEntityIsNotFound() {
			final List<NewsModel> storage = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(dataSource.getNews()).thenReturn(storage);

			assertFalse(repository.isExistById(99L));
		}

		@Test
		void isExistById_shouldReturnTrue_whenEntityIsFound() {
			final List<NewsModel> storage = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(dataSource.getNews()).thenReturn(storage);

			assertTrue(repository.isExistById(2L));
		}
	}

	private NewsModel createTestNews(Long newsId) {
		return new NewsModel(
			newsId,
			"Title",
			"Content",
			LocalDateTime.of(2023, 7, 17, 16, 30, 0),
			LocalDateTime.of(2023, 7, 17, 16, 30, 0),
			1L
		);
	}
}