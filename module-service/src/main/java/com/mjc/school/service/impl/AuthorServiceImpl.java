package com.mjc.school.service.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.domain.AuthorModel;
import com.mjc.school.repository.impl.AuthorRepositoryImpl;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorRequestDTO;
import com.mjc.school.service.dto.AuthorResponseDTO;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.service.utility.Mapper;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

	private static final String UNSUPPORTED_MESSAGE = "Operation is not implemented yet";
	private final AuthorRepository authorRepository;
	private final Mapper mapper;

	public AuthorServiceImpl() {
		this(new AuthorRepositoryImpl());
	}

	public AuthorServiceImpl(final AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
		mapper = Mapper.getInstance();
	}

	@Override
	public AuthorResponseDTO create(final AuthorRequestDTO authorRequestDTO) throws ValidationException {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public AuthorResponseDTO readById(final Long id) throws EntityNotFoundException, ValidationException {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public List<AuthorResponseDTO> readAll() {
		List<AuthorModel> allAuthors = authorRepository.readAll();
		return allAuthors.stream()
			.map(mapper::convertEntityToResponseDto)
			.toList();
	}

	@Override
	public AuthorResponseDTO update(final AuthorRequestDTO authorRequestDTO)
		throws EntityNotFoundException, ValidationException {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public Boolean delete(final Long id) throws ValidationException {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}
}