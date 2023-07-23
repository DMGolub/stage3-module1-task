package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.factory.ServiceFactory;

import java.util.List;

public class AuthorController implements Controller<AuthorRequestDto, AuthorResponseDto> {

	private static final String UNSUPPORTED_MESSAGE = "Operation is not implemented yet";

	private final Service<AuthorRequestDto, AuthorResponseDto> authorService;

	public AuthorController() {
		this(ServiceFactory.getInstance().getAuthorService());
	}

	public AuthorController(final Service<AuthorRequestDto, AuthorResponseDto> authorService) {
		this.authorService = authorService;
	}

	@Override
	public AuthorResponseDto create(final AuthorRequestDto authorRequestDto) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public List<AuthorResponseDto> readAll() {
		return authorService.readAll();
	}

	@Override
	public AuthorResponseDto readById(final Long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public AuthorResponseDto update(final AuthorRequestDto authorRequestDto) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public Boolean delete(final Long id) {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}
}