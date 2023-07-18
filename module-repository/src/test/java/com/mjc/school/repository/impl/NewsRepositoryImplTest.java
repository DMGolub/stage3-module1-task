package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.domain.News;
import com.mjc.school.repository.utility.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NewsRepositoryImplTest {

	private final DataSource dataSource = Mockito.mock(DataSource.class);

	@Nested
	class TestSave {

		@Test
		void save_shouldSaveNewsAndReturnNewsWithId1_whenStorageIsEmpty() {
			final List<News> storage = new ArrayList<>();
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);
			News news = createTestNews(null);
			News expected = createTestNews(1L);

			assertEquals(expected, repository.save(news));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void save_shouldSaveNewsAndReturnNewsWithId3_whenStorageContainsTwoNews() {
			final List<News> storage = new ArrayList<>();
			storage.add(createTestNews(1L));
			storage.add(createTestNews(2L));
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);
			News news = createTestNews(null);
			News expected = createTestNews(3L);

			assertEquals(expected, repository.save(news));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void save_shouldSaveNewsAndReturnNewsWithId4_whenStorageContainsTwoNewsWithId12And5() {
			final List<News> storage = new ArrayList<>();
			storage.add(createTestNews(12L));
			storage.add(createTestNews(15L));
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);
			News news = createTestNews(null);
			News expected = createTestNews(16L);

			assertEquals(expected, repository.save(news));
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestGetById {

		@Test
		void getById_shouldReturnEmptyOptional_whenNewsNotFound() {
			final List<News> storage = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);

			assertEquals(Optional.empty(), repository.getById(3L));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void getById_shouldReturnEntity_whenNewsWithGivenIdExists() {
			final long id = 2L;
			final News expected = createTestNews(id);
			final List<News> storage = Arrays.asList(createTestNews(1L), expected);
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);

			assertEquals(Optional.of(expected), repository.getById(id));
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestGetAll {

		@Test
		void getAll_shouldReturnEmptyList_whenStorageIsEmpty() {
			NewsRepository repository = new NewsRepositoryImpl(dataSource);
			when(dataSource.getNews()).thenReturn(Collections.emptyList());

			assertEquals(Collections.emptyList(), repository.getAll());
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void getAll_shouldReturnTwoEntities_whenThereAreTwoNewsInTheStorage() {
			final List<News> storage = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);

			assertEquals(storage, repository.getAll());
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestUpdate {

		@Test
		void update_shouldReturnEmptyOptional_whenNewsWithGivenIdNotFound() {
			final List<News> storage = new ArrayList<>();
			storage.add(createTestNews(1L));
			storage.add(createTestNews(2L));
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);
			News updated = createTestNews(99L);

			assertEquals(Optional.empty(), repository.update(updated));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void update() {
			final List<News> storage = new ArrayList<>();
			storage.add(createTestNews(1L));
			storage.add(createTestNews(2L));
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);
			final News updated = createTestNews(2L);
			updated.setTitle("Updated title");
			updated.setContent("Updated content");
			updated.setAuthorId(2L);
			List<News> expected = Arrays.asList(createTestNews(1L), updated);

			assertEquals(Optional.of(updated), repository.update(updated));
			assertEquals(expected, storage);
			verify(dataSource, times(1)).getNews();
		}
	}

	@Nested
	class TestDelete {

		@Test
		void delete_shouldReturnFalse_whenThereIsNoNewsWithGivenId() {
			final List<News> storage = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);

			assertFalse(repository.delete(99));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void delete_shouldReturnFalse_whenStorageIsEmpty() {
			when(dataSource.getNews()).thenReturn(Collections.emptyList());

			NewsRepository repository = new NewsRepositoryImpl(dataSource);

			assertFalse(repository.delete(99));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void delete_shouldReturnTrue_whenNewsWithGivenIdDeleted() {
			final List<News> storage = new ArrayList<>();
			storage.add(createTestNews(1L));
			storage.add(createTestNews(2L));
			storage.add(createTestNews(3L));
			storage.add(createTestNews(4L));
			storage.add(createTestNews(5L));
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);

			assertTrue(repository.delete(3L));
			verify(dataSource, times(1)).getNews();
		}

		@Test
		void delete_shouldDeleteEntity_whenItIsSingleEntityInTheStorage() {
			final List<News> storage = new ArrayList<>();
			storage.add(createTestNews(3L));
			when(dataSource.getNews()).thenReturn(storage);

			NewsRepository repository = new NewsRepositoryImpl(dataSource);

			assertTrue(repository.delete(3L));
			verify(dataSource, times(1)).getNews();
		}
	}

	private News createTestNews(Long newsId) {
		return new News(
			newsId,
			"Title",
			"Content",
			LocalDateTime.of(2023, 7, 17, 16, 30, 0),
			LocalDateTime.of(2023, 7, 17, 16, 30, 0),
			1L
		);
	}
}