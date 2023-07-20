package com.mjc.school.service.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.domain.NewsModel;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class NewsServiceImplTest {

	private final AuthorRepository authorRepository = mock(AuthorRepository.class);
	private final NewsRepository newsRepository = mock(NewsRepository.class);
	private final Service<NewsRequestDTO, NewsResponseDTO> newsService =
		new NewsServiceImpl(authorRepository, newsRepository);

	@Nested
	class TestSave {

		@Test
		void save_shouldThrowValidationException_whenTitleViolatesLengthConstraints() {
			NewsRequestDTO shortTitle = new NewsRequestDTO(
				null,
				"T",
				"Some valid content",
				2L
			);
			NewsRequestDTO longTitle = new NewsRequestDTO(
				null,
				"T".repeat(50),
				"Some valid content",
				2L
			);

			assertThrows(ValidationException.class, () -> newsService.save(shortTitle));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
			assertThrows(ValidationException.class, () -> newsService.save(longTitle));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void save_shouldThrowValidationException_whenContentViolatesLengthConstraints() {
			NewsRequestDTO shortContent = new NewsRequestDTO(
				null,
				"Some valid title",
				"C",
				2L
			);
			NewsRequestDTO longContent = new NewsRequestDTO(
				null,
				"Some valid title",
				"C".repeat(300),
				2L
			);

			assertThrows(ValidationException.class, () -> newsService.save(shortContent));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
			assertThrows(ValidationException.class, () -> newsService.save(longContent));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void save_shouldThrowValidationException_whenAuthorIdViolatesConstraints() {
			NewsRequestDTO nullAuthorId = new NewsRequestDTO(
				null,
				"Some valid title",
				"Some valid content",
				null
			);
			NewsRequestDTO negativeAuthorId = new NewsRequestDTO(
				null,
				"Some valid title",
				"Some valid content",
				-2L
			);

			assertThrows(ValidationException.class, () -> newsService.save(nullAuthorId));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
			assertThrows(ValidationException.class, () -> newsService.save(negativeAuthorId));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void save_shouldThrowEntityNotFoundException_whenAuthorNotFound() {
			final long authorId = 99L;
			NewsRequestDTO request = new NewsRequestDTO(
				null,
				"Some valid title",
				"Some valid content",
				authorId
			);

			when(authorRepository.isPresent(authorId)).thenReturn(false);

			assertThrows(EntityNotFoundException.class, () -> newsService.save(request));
			verify(authorRepository, times(1)).isPresent(authorId);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void save_shouldReturnSavedEntity_whenValidRequestDtoProvided() {
			final long authorId = 1L;
			NewsRequestDTO request = new NewsRequestDTO(
				null,
				"Some valid title",
				"Some valid content",
				authorId
			);
			when(newsRepository.create(any())).then(AdditionalAnswers.returnsFirstArg());
			when(authorRepository.isPresent(authorId)).thenReturn(true);

			NewsResponseDTO response = newsService.save(request);

			verify(authorRepository, times(1)).isPresent(authorId);
			verify(newsRepository, times(1)).create(any());
			assertNotNull(response);
			assertEquals(request.getTitle(), response.getTitle());
			assertEquals(request.getContent(), response.getContent());
			assertNotNull(response.getCreateDate());
			assertNotNull(response.getLastUpdateDate());
			assertEquals(request.getAuthorId(), response.getAuthorId());
		}
	}

	@Nested
	class TestGetById {

		@Test
		void getById_shouldThrowValidationException_whenIdIsNegative() {
			final long id = -5L;

			assertThrows(ValidationException.class, () -> newsService.getById(id));
			verifyNoInteractions(newsRepository);
		}

		@Test
		void getById_shouldThrowValidationException_whenIdIsZero() {
			final long id = 0;

			assertThrows(ValidationException.class, () -> newsService.getById(id));
			verifyNoInteractions(newsRepository);
		}

		@Test
		void getById_shouldThrowEntityNotFoundException_whenThereIsNoEntityWithGivenId() {
			final long id = 99L;
			when(newsRepository.readById(id)).thenReturn(null);

			assertThrows(EntityNotFoundException.class, () -> newsService.getById(id));
			verify(newsRepository, times(1)).readById(id);
		}

		@Test
		void getById_shouldReturnDTO_whenEntityWithGivenIdIsFound() {
			final long id = 2L;
			final NewsModel toBeFound = createTestNews(id);
			when(newsRepository.readById(id)).thenReturn(toBeFound);

			NewsResponseDTO expected = newsToDTO(toBeFound);

			assertEquals(expected, newsService.getById(id));
			verify(newsRepository, times(1)).readById(id);
		}
	}

	@Nested
	class TestGetAll {

		@Test
		void getAll_shouldReturnEmptyDTOList_whenRepositoryReturnsEmptyList() {
			when(newsRepository.readAll()).thenReturn(new ArrayList<>());

			List<NewsResponseDTO> expected = new ArrayList<>();

			assertEquals(expected, newsService.getAll());
			verify(newsRepository, times(1)).readAll();
		}

		@Test
		void getAll_shouldReturnTwoDTOs_whenRepositoryReturnsTwoEntities() {
			final List<NewsModel> allNews = Arrays.asList(
				createTestNews(1L),
				createTestNews(2L)
			);
			when(newsRepository.readAll()).thenReturn(allNews);

			List<NewsResponseDTO> expected = newsListToNewsDTOList(allNews);

			assertEquals(expected, newsService.getAll());
			verify(newsRepository, times(1)).readAll();
		}
	}

	@Nested
	class TestUpdate {

		@Test
		void update_shouldThrowValidationException_whenIdIsNegative() {
			final long id = -5L;
			NewsRequestDTO request = crateTestRequest(id);

			assertThrows(ValidationException.class, () -> newsService.update(request));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void update_shouldThrowValidationException_whenIdIsZero() {
			final long id = 0;
			NewsRequestDTO request = crateTestRequest(id);

			assertThrows(ValidationException.class, () -> newsService.update(request));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void update_shouldThrowEntityNotFoundException_whenAuthorNotFound() {
			final long id = 5;
			NewsRequestDTO request = crateTestRequest(id);
			when(authorRepository.isPresent(request.getAuthorId())).thenReturn(false);

			assertThrows(EntityNotFoundException.class, () -> newsService.update(request));
			verify(authorRepository, times(1)).isPresent(request.getAuthorId());
			verifyNoInteractions(newsRepository);
		}

		@Test
		void update_shouldThrowEntityNotFoundException_whenEntityWithGivenIdNotFound() {
			final long id = 99;
			NewsRequestDTO request = crateTestRequest(id);
			when(authorRepository.isPresent(request.getAuthorId())).thenReturn(true);
			when(newsRepository.readById(id)).thenReturn(null);

			assertThrows(EntityNotFoundException.class, () -> newsService.update(request));
			verify(authorRepository, times(1)).isPresent(request.getAuthorId());
			verify(newsRepository, times(1)).readById(id);
		}

		@Test
		void update_shouldThrowValidationException_whenTitleViolatesLengthConstraints() {
			NewsRequestDTO shortTitle = new NewsRequestDTO(
				1L,
				"T",
				"Some valid content",
				2L
			);
			NewsRequestDTO longTitle = new NewsRequestDTO(
				1L,
				"T".repeat(50),
				"Some valid content",
				2L
			);

			assertThrows(ValidationException.class, () -> newsService.update(shortTitle));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
			assertThrows(ValidationException.class, () -> newsService.update(longTitle));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void update_shouldThrowValidationException_whenContentViolatesLengthConstraints() {
			NewsRequestDTO shortContent = new NewsRequestDTO(
				1L,
				"Some valid title",
				"C",
				2L
			);
			NewsRequestDTO longContent = new NewsRequestDTO(
				1L,
				"Some valid title",
				"C".repeat(300),
				2L
			);

			assertThrows(ValidationException.class, () -> newsService.update(shortContent));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
			assertThrows(ValidationException.class, () -> newsService.update(longContent));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void update_shouldThrowValidationException_whenAuthorIdViolatesConstraints() {
			NewsRequestDTO nullAuthorId = new NewsRequestDTO(
				1L,
				"Some valid title",
				"Some valid content",
				null
			);
			NewsRequestDTO negativeAuthorId = new NewsRequestDTO(
				1L,
				"Some valid title",
				"Some valid content",
				-2L
			);

			assertThrows(ValidationException.class, () -> newsService.update(nullAuthorId));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
			assertThrows(ValidationException.class, () -> newsService.update(negativeAuthorId));
			verifyNoInteractions(authorRepository);
			verifyNoInteractions(newsRepository);
		}

		@Test
		void update_shouldReturnUpdatedEntity_whenValidRequestDtoProvided() {
			final long id = 1L;
			NewsRequestDTO request = new NewsRequestDTO(
				id,
				"Some updated title",
				"Some updated content",
				2L
			);
			NewsModel previous = new NewsModel(
				id,
				"Some old title",
				"Some old content",
				LocalDateTime.of(2023, 7, 17, 16, 30, 0),
				LocalDateTime.of(2023, 7, 17, 16, 30, 0),
				1L
			);
			NewsModel updated = new NewsModel(
				id,
				"Some updated title",
				"Some updated content",
				LocalDateTime.of(2023, 7, 17, 16, 30, 0),
				LocalDateTime.now(),
				2L
			);
			when(authorRepository.isPresent(request.getAuthorId())).thenReturn(true);
			when(newsRepository.readById(id)).thenReturn(previous);
			when(newsRepository.update(any())).thenReturn(updated);

			NewsResponseDTO response = newsService.update(request);

			verify(authorRepository, times(1)).isPresent(request.getAuthorId());
			verify(newsRepository, times(1)).readById(id);
			verify(newsRepository, times(1)).update(any());
			assertNotNull(response);
			assertEquals(request.getTitle(), response.getTitle());
			assertEquals(request.getContent(), response.getContent());
			assertEquals(previous.getCreateDate(), response.getCreateDate());
			assertEquals(request.getAuthorId(), response.getAuthorId());
		}
	}

	@Nested
	class TestDelete {

		@Test
		void delete_shouldThrowValidationException_whenIdIsNegative() {
			final long id = -5L;

			assertThrows(ValidationException.class, () -> newsService.delete(id));
			verifyNoInteractions(newsRepository);
		}

		@Test
		void delete_shouldThrowValidationException_whenIdIsZero() {
			final long id = 0;

			assertThrows(ValidationException.class, () -> newsService.delete(id));
			verifyNoInteractions(newsRepository);
		}

		@Test
		void delete_shouldReturnTrue_whenRepositoryDeletesEntityById() {
			final long id = 5L;
			when(newsRepository.delete(id)).thenReturn(true);

			assertTrue(newsService.delete(id));
			verify(newsRepository, times(1)).delete(id);
		}

		@Test
		void delete_shouldReturnFalse_whenRepositoryDoesNotDeletesEntityById() {
			final long id = 99L;
			when(newsRepository.delete(id)).thenReturn(false);

			assertFalse(newsService.delete(id));
			verify(newsRepository, times(1)).delete(id);
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

	private NewsRequestDTO crateTestRequest(Long newsId) {
		return new NewsRequestDTO(newsId, "Title", "Content", 1L);
	}

	private NewsResponseDTO newsToDTO(NewsModel news) {
		return new NewsResponseDTO(
			news.getId(),
			news.getTitle(),
			news.getContent(),
			news.getCreateDate(),
			news.getLastUpdateDate(),
			news.getAuthorId()
		);
	}

	private List<NewsResponseDTO> newsListToNewsDTOList(List<NewsModel> news) {
		return news.stream()
			.map(this::newsToDTO)
			.collect(Collectors.toList());
	}
}