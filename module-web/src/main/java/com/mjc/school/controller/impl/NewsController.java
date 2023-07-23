package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.factory.ServiceFactory;

import java.util.List;

public class NewsController implements Controller<NewsRequestDto, NewsResponseDto> {

	private final Service<NewsRequestDto, NewsResponseDto> newsService;

	public NewsController() {
		this(ServiceFactory.getInstance().getNewsService());
	}

	public NewsController(final Service<NewsRequestDto, NewsResponseDto> newsService) {
		this.newsService = newsService;
	}

	@Override
	public NewsResponseDto create(final NewsRequestDto request) {
		return newsService.create(request);
	}

	@Override
	public List<NewsResponseDto> readAll() {
		return newsService.readAll();
	}

	@Override
	public NewsResponseDto readById(final Long id) {
		return newsService.readById(id);
	}

	@Override
	public NewsResponseDto update(final NewsRequestDto request) {
		return newsService.update(request);
	}

	@Override
	public Boolean delete(final Long id) {
		return newsService.deleteById(id);
	}
}