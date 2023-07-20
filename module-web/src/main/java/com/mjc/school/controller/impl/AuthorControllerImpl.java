package com.mjc.school.controller.impl;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorRequestDTO;
import com.mjc.school.service.dto.AuthorResponseDTO;
import com.mjc.school.service.impl.AuthorServiceImpl;

import java.util.List;

public class AuthorControllerImpl implements AuthorController {

	private static final String UNSUPPORTED_MESSAGE = "Operation is not implemented yet";

	private final AuthorService authorService;

	public AuthorControllerImpl() {
		this(new AuthorServiceImpl());
	}

	public AuthorControllerImpl(final AuthorService authorService) {
		this.authorService = authorService;
	}

	@Override
	public AuthorResponseDTO create(final AuthorRequestDTO authorRequestDTO) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public List<AuthorResponseDTO> readAll() {
		return authorService.readAll();
	}

	@Override
	public AuthorResponseDTO readById(final Long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public AuthorResponseDTO update(final AuthorRequestDTO authorRequestDTO) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public Boolean delete(final Long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}
}