package com.mjc.school.service.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.domain.NewsModel;
import com.mjc.school.repository.impl.AuthorRepositoryImpl;
import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.service.utility.Mapper;
import com.mjc.school.service.utility.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class NewsServiceImpl implements NewsService {

	private static final String NEWS_ID_NAME = "news id";
	private static final String NEWS_ERROR_MESSAGE = "Can not find news by id: ";
	private static final String AUTHOR_ERROR_MESSAGE = "Can not find author by id = ";

	private final AuthorRepository authorRepository;
	private final NewsRepository newsRepository;
	private final Mapper mapper;
	private final Validator newsValidator;

	public NewsServiceImpl() {
		this(new AuthorRepositoryImpl(), new NewsRepositoryImpl());
	}

	public NewsServiceImpl(
		final AuthorRepository authorRepository,
		final NewsRepository newsRepository
	) {
		this.authorRepository = authorRepository;
		this.newsRepository = newsRepository;
		this.mapper = Mapper.getInstance();
		this.newsValidator = Validator.getInstance();
	}

	@Override
	public NewsResponseDTO create(final NewsRequestDTO request) throws EntityNotFoundException, ValidationException {
		newsValidator.validateNewsRequestDTO(request);
		checkAuthorExists(request.getAuthorId());
		final NewsModel news = mapper.convertRequestDtoToEntity(request);
		final LocalDateTime now = LocalDateTime.now();
		news.setCreateDate(now);
		news.setLastUpdateDate(now);
		return mapper.convertEntityToResponseDto(newsRepository.create(news));
	}

	@Override
	public NewsResponseDTO readById(final Long id) throws EntityNotFoundException, ValidationException {
		newsValidator.validatePositive(id, NEWS_ID_NAME);
		NewsModel news = newsRepository.readById(id);
		if (news == null) {
			throw new EntityNotFoundException(NEWS_ERROR_MESSAGE + id);
		}
		return mapper.convertEntityToResponseDto(news);
	}

	@Override
	public List<NewsResponseDTO> readAll() {
		List<NewsModel> allNews = newsRepository.readAll();
		return allNews.stream()
			.map(mapper::convertEntityToResponseDto)
			.toList();
	}

	@Override
	public NewsResponseDTO update(final NewsRequestDTO request) throws EntityNotFoundException, ValidationException {
		newsValidator.validateNewsRequestDTO(request);
		final Long id = request.getId();
		newsValidator.validateNotNull(id, NEWS_ID_NAME);
		newsValidator.validatePositive(id, NEWS_ID_NAME);
		checkAuthorExists(request.getAuthorId());
		final NewsModel news = newsRepository.readById(id);
		if (news == null) {
			throw new EntityNotFoundException(NEWS_ERROR_MESSAGE + id);
		}
		news.setTitle(request.getTitle());
		news.setContent(request.getContent());
		news.setLastUpdateDate(LocalDateTime.now());
		news.setAuthorId(request.getAuthorId());
		return mapper.convertEntityToResponseDto(newsRepository.update(news));
	}

	private void checkAuthorExists(final Long authorId) {
		if (!authorRepository.isPresent(authorId)) {
			throw new EntityNotFoundException(AUTHOR_ERROR_MESSAGE + authorId);
		}
	}

	@Override
	public Boolean delete(final Long id) throws ValidationException {
		newsValidator.validatePositive(id, NEWS_ID_NAME);
		return newsRepository.delete(id);
	}
}