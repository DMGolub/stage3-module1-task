package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.utility.DtoValidator;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mjc.school.service.exception.ServiceErrorCode.ENTITY_NOT_FOUND_BY_ID;

public class NewsService implements Service<NewsRequestDto, NewsResponseDto> {

	private static final String NEWS_ID_NAME = "news id";
	private static final String NEWS_ENTITY_NAME = "news";
	private static final String AUTHOR_ENTITY_NAME = "author";
	private final Repository<AuthorModel> authorRepository;
	private final Repository<NewsModel> newsRepository;
	private final ModelMapper modelMapper = Mappers.getMapper(ModelMapper.class);
	private final DtoValidator dtoValidator;

	public NewsService() {
		this(
			RepositoryFactory.getInstance().getAuthorRepository(),
			RepositoryFactory.getInstance().getNewsRepository()
		);
	}

	NewsService(
		final Repository<AuthorModel> authorRepository,
		final Repository<NewsModel> newsRepository
	) {
		this.authorRepository = authorRepository;
		this.newsRepository = newsRepository;
		this.dtoValidator = DtoValidator.getInstance();
	}

	@Override
	public NewsResponseDto create(final NewsRequestDto request) throws EntityNotFoundException, ValidationException {
		dtoValidator.validateNewsRequestDTO(request);
		checkAuthorExists(request.authorId());
		final NewsModel news = modelMapper.requestDtoToNews(request);
		final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		news.setCreateDate(now);
		news.setLastUpdateDate(now);
		return modelMapper.newsToResponseDto(newsRepository.create(news));
	}

	@Override
	public NewsResponseDto readById(final Long id) throws EntityNotFoundException, ValidationException {
		dtoValidator.validateId(id, NEWS_ID_NAME);
		if (newsRepository.isExistById(id)) {
			NewsModel news = newsRepository.readById(id);
			return modelMapper.newsToResponseDto(news);
		} else {
			throw new EntityNotFoundException(
				String.format(ENTITY_NOT_FOUND_BY_ID.getMessage(), NEWS_ENTITY_NAME, id),
				ENTITY_NOT_FOUND_BY_ID.getCode()
			);
		}
	}

	@Override
	public List<NewsResponseDto> readAll() {
		return modelMapper.newsListToDtoList(newsRepository.readAll());
	}

	@Override
	public NewsResponseDto update(final NewsRequestDto request) throws EntityNotFoundException, ValidationException {
		final Long id = request.id();
		dtoValidator.validateId(id, NEWS_ID_NAME);
		dtoValidator.validateNewsRequestDTO(request);
		checkAuthorExists(request.authorId());
		if (newsRepository.isExistById(id)) {
			final NewsModel news = modelMapper.requestDtoToNews(request);
			news.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
			return modelMapper.newsToResponseDto(newsRepository.update(news));
		} else {
			throw new EntityNotFoundException(
				String.format(ENTITY_NOT_FOUND_BY_ID.getMessage(), NEWS_ENTITY_NAME, id),
				ENTITY_NOT_FOUND_BY_ID.getCode()
			);
		}
	}

	@Override
	public Boolean deleteById(final Long id) throws EntityNotFoundException, ValidationException {
		dtoValidator.validateId(id, NEWS_ID_NAME);
		if (newsRepository.isExistById(id)) {
			return newsRepository.deleteById(id);
		} else {
			throw new EntityNotFoundException(
				String.format(ENTITY_NOT_FOUND_BY_ID.getMessage(), NEWS_ENTITY_NAME, id),
				ENTITY_NOT_FOUND_BY_ID.getCode()
			);
		}
	}

	private void checkAuthorExists(final Long authorId) throws EntityNotFoundException {
		dtoValidator.validateId(authorId, NEWS_ID_NAME);
		if (!authorRepository.isExistById(authorId)) {
			throw new EntityNotFoundException(
				String.format(ENTITY_NOT_FOUND_BY_ID.getMessage(), AUTHOR_ENTITY_NAME, authorId),
				ENTITY_NOT_FOUND_BY_ID.getCode()
			);
		}
	}
}