package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.impl.NewsServiceImpl;

import java.util.List;

public class NewsControllerImpl implements NewsController {

	private final NewsService newsService;

	public NewsControllerImpl() {
		this(new NewsServiceImpl());
	}

	public NewsControllerImpl(NewsService newsService) {
		this.newsService = newsService;
	}

	@Override
	public NewsResponseDTO create(final NewsRequestDTO request) {
		return newsService.create(request);
	}

	@Override
	public List<NewsResponseDTO> readAll() {
		return newsService.readAll();
	}

	@Override
	public NewsResponseDTO readById(final Long id) {
		return newsService.readById(id);
	}

	@Override
	public NewsResponseDTO update(final NewsRequestDTO request) {
		return newsService.update(request);
	}

	@Override
	public Boolean delete(final Long id) {
		return newsService.delete(id);
	}
}