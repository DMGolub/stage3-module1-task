package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;

import java.util.List;

public class NewsControllerImpl implements NewsController {

	private final NewsService newsService;

	public NewsControllerImpl(NewsService newsService) {
		this.newsService = newsService;
	}

	@Override
	public NewsResponseDTO save(final NewsRequestDTO request) {
		return newsService.save(request);
	}

	@Override
	public List<NewsResponseDTO> getAll() {
		return newsService.getAll();
	}

	@Override
	public NewsResponseDTO getById(final Long id) {
		return newsService.getById(id);
	}

	@Override
	public NewsResponseDTO update(final NewsRequestDTO request) {
		return newsService.update(request);
	}

	@Override
	public boolean delete(final Long id) {
		return newsService.delete(id);
	}
}