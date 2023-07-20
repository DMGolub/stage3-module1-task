package com.mjc.school.service.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.domain.Author;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.AuthorRequestDTO;
import com.mjc.school.service.dto.AuthorResponseDTO;
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

class AuthorServiceImplTest {

	private final AuthorRepository authorRepository = mock(AuthorRepository.class);
	private final Service<AuthorRequestDTO, AuthorResponseDTO> authorService =
		new AuthorServiceImpl(authorRepository);

	@Nested
	class TestGetAll {

		@Test
		void getAll_shouldReturnEmptyDTOList_whenRepositoryReturnsEmptyList() {
			when(authorRepository.getAll()).thenReturn(new ArrayList<>());

			List<AuthorResponseDTO> expected = new ArrayList<>();

			assertEquals(expected, authorService.getAll());
			verify(authorRepository, times(1)).getAll();
		}

		@Test
		void getAll_shouldReturnTwoDTO_whenRepositoryReturnsTwoEntities() {
			final List<Author> allAuthors = Arrays.asList(
				createTestAuthor(1L),
				createTestAuthor(2L)
			);
			when(authorRepository.getAll()).thenReturn(allAuthors);

			List<AuthorResponseDTO> expected = authorListToAuthorDTOList(allAuthors);

			assertEquals(expected, authorService.getAll());
			verify(authorRepository, times(1)).getAll();
		}
	}

	private Author createTestAuthor(final long id) {
		return new Author(id, "Author Name");
	}

	private AuthorResponseDTO authorToDTO(Author author) {
		return new AuthorResponseDTO(
			author.getId(),
			author.getName()
		);
	}

	private List<AuthorResponseDTO> authorListToAuthorDTOList(List<Author> authors) {
		return authors.stream()
			.map(this::authorToDTO)
			.collect(Collectors.toList());
	}
}