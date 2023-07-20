package com.mjc.school.service.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.domain.News;
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

	private final NewsRepository newsRepository;
	private final Mapper mapper;

	public NewsServiceImpl(final NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
		this.mapper = Mapper.getInstance();
	}

	@Override
	public NewsResponseDTO save(final NewsRequestDTO request) throws ValidationException {
		Validator.validateNewsRequestDTO(request);
		final News news = mapper.convertRequestDtoToEntity(request);
		final LocalDateTime now = LocalDateTime.now();
		news.setCreateDate(now);
		news.setLastUpdateDate(now);
		return mapper.convertEntityToResponseDto(newsRepository.save(news));
	}

	@Override
	public NewsResponseDTO getById(final long id) throws EntityNotFoundException, ValidationException {
		Validator.validatePositive(id, NEWS_ID_NAME);
		News news = newsRepository.getById(id)
			.orElseThrow(() -> new EntityNotFoundException("Can not find news by id: " + id));
		return mapper.convertEntityToResponseDto(news);
	}

	@Override
	public List<NewsResponseDTO> getAll() {
		List<News> allNews = newsRepository.getAll();
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
		final News news = newsRepository.getById(id).orElseThrow(
			() -> new EntityNotFoundException("Can not find news by id = " + id));
		news.setTitle(request.getTitle());
		news.setContent(request.getContent());
		news.setLastUpdateDate(LocalDateTime.now());
		news.setAuthorId(request.getAuthorId());
		return mapper.convertEntityToResponseDto(newsRepository.update(news).orElse(null));
	}

	@Override
	public boolean delete(final long id) throws ValidationException {
		Validator.validatePositive(id, NEWS_ID_NAME);
		return newsRepository.delete(id);
	}
}