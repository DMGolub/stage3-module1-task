package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.utility.DtoValidator;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class AuthorService implements Service<AuthorRequestDto, AuthorResponseDto> {

	private static final String UNSUPPORTED_MESSAGE = "Operation is not implemented yet";
	private final Repository<AuthorModel> authorRepository;
	private final ModelMapper modelMapper = Mappers.getMapper(ModelMapper.class);
	private final DtoValidator dtoValidator;

	public AuthorService() {
		this(RepositoryFactory.getInstance().getAuthorRepository());
	}

	AuthorService(final Repository<AuthorModel> authorRepository) {
		this.authorRepository = authorRepository;
		this.dtoValidator = DtoValidator.getInstance();
	}

	@Override
	public AuthorResponseDto create(final AuthorRequestDto authorRequestDTO) throws ValidationException {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public AuthorResponseDto readById(final Long id) throws EntityNotFoundException, ValidationException {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public List<AuthorResponseDto> readAll() {
		return modelMapper.authorListToDtoList(authorRepository.readAll());
	}

	@Override
	public AuthorResponseDto update(final AuthorRequestDto authorRequestDTO)
		throws EntityNotFoundException, ValidationException {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public Boolean deleteById(final Long id) throws ValidationException {
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}
}