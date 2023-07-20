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

	private final AuthorRepository authorRepository;
	private final NewsRepository newsRepository;
	private final Mapper mapper;

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
	}

	@Override
	public NewsResponseDTO save(final NewsRequestDTO request) throws EntityNotFoundException, ValidationException {
		Validator.validateNewsRequestDTO(request);
		checkAuthorExists(request.getAuthorId());
		final NewsModel news = mapper.convertRequestDtoToEntity(request);
		final LocalDateTime now = LocalDateTime.now();
		news.setCreateDate(now);
		news.setLastUpdateDate(now);
		return mapper.convertEntityToResponseDto(newsRepository.create(news));
	}

	@Override
	public NewsResponseDTO getById(final long id) throws EntityNotFoundException, ValidationException {
		Validator.validatePositive(id, NEWS_ID_NAME);
		NewsModel news = newsRepository.readById(id)
			.orElseThrow(() -> new EntityNotFoundException("Can not find news by id: " + id));
		return mapper.convertEntityToResponseDto(news);
	}

	@Override
	public List<NewsResponseDTO> getAll() {
		List<NewsModel> allNews = newsRepository.readAll();
		return allNews.stream()
			.map(mapper::convertEntityToResponseDto)
			.toList();
	}

	@Override
	public NewsResponseDTO update(final NewsRequestDTO request) throws EntityNotFoundException, ValidationException {
		Validator.validateNewsRequestDTO(request);
		final Long id = request.getId();
		Validator.validateNotNull(id, NEWS_ID_NAME);
		Validator.validatePositive(id, NEWS_ID_NAME);
		checkAuthorExists(request.getAuthorId());
		final NewsModel news = newsRepository.readById(id).orElseThrow(
			() -> new EntityNotFoundException("Can not find news by id = " + id));
		news.setTitle(request.getTitle());
		news.setContent(request.getContent());
		news.setLastUpdateDate(LocalDateTime.now());
		news.setAuthorId(request.getAuthorId());
		return mapper.convertEntityToResponseDto(newsRepository.update(news).orElse(null));
	}

	private void checkAuthorExists(final Long authorId) {
		if (!authorRepository.isPresent(authorId)) {
			throw new EntityNotFoundException("Can not find author by id = " + authorId);
		}
	}

	@Override
	public boolean delete(final long id) throws ValidationException {
		Validator.validatePositive(id, NEWS_ID_NAME);
		return newsRepository.delete(id);
	}
}