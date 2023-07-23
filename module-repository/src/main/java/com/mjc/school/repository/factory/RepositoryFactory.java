package com.mjc.school.repository.factory;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.impl.NewsRepository;

public class RepositoryFactory {

	private static RepositoryFactory instance;

	private RepositoryFactory() {
		// Empty
	}

	public static RepositoryFactory getInstance() {
		if (instance == null) {
			instance = new RepositoryFactory();
		}
		return instance;
	}

	public Repository<NewsModel> getNewsRepository() {
		return new NewsRepository();
	}

	public Repository<AuthorModel> getAuthorRepository() {
		return new AuthorRepository();
	}
}