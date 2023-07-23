package com.mjc.school.service.factory;

import com.mjc.school.service.Service;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.impl.AuthorService;
import com.mjc.school.service.impl.NewsService;

public class ServiceFactory {

	private static ServiceFactory instance;

	private ServiceFactory() {
		// Empty
	}

	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public Service<AuthorRequestDto, AuthorResponseDto> getAuthorService() {
		return new AuthorService();
	}

	public Service<NewsRequestDto, NewsResponseDto> getNewsService() {
		return new NewsService();
	}
}