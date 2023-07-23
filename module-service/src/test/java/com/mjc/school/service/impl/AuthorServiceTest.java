package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthorServiceTest {

	private final Repository<AuthorModel> authorRepository = mock(AuthorRepository.class);
	private final Service<AuthorRequestDto, AuthorResponseDto> authorService =
		new AuthorService(authorRepository);

	@Nested
	class TesReadAll {

		@Test
		void readAll_shouldReturnEmptyDTOList_whenRepositoryReturnsEmptyList() {
			when(authorRepository.readAll()).thenReturn(new ArrayList<>());

			List<AuthorResponseDto> expected = new ArrayList<>();

			assertEquals(expected, authorService.readAll());
			verify(authorRepository, times(1)).readAll();
		}

		@Test
		void readAll_shouldReturnTwoDTO_whenRepositoryReturnsTwoEntities() {
			final List<AuthorModel> allAuthors = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(authorRepository.readAll()).thenReturn(allAuthors);

			List<AuthorResponseDto> expected = authorListToAuthorDTOList(allAuthors);

			assertEquals(expected, authorService.readAll());
			verify(authorRepository, times(1)).readAll();
		}
	}

	private AuthorModel createTestAuthor(final long id) {
		return new AuthorModel(id, "Author Name");
	}

	private AuthorResponseDto authorToDTO(AuthorModel author) {
		return new AuthorResponseDto(
			author.getId(),
			author.getName()
		);
	}

	private List<AuthorResponseDto> authorListToAuthorDTOList(List<AuthorModel> authors) {
		return authors.stream()
			.map(this::authorToDTO)
			.collect(Collectors.toList());
	}
}