package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.domain.News;
import com.mjc.school.repository.utility.DataSource;
import com.mjc.school.repository.utility.Utilities;

import java.util.List;
import java.util.Optional;

public class NewsRepositoryImpl implements NewsRepository {

	private final DataSource dataSource;

	public NewsRepositoryImpl() {
		dataSource = DataSource.getInstance();
	}

	public NewsRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public News save(final News news) {
		final List<News> allNews = dataSource.getNews();
		news.setId(Utilities.getMaxId(allNews) + 1);
		allNews.add(news);
		return news;
	}

	@Override
	public Optional<News> getById(final long id) {
		final List<News> allNews = dataSource.getNews();
		return allNews.stream()
			.filter(n -> n.getId().equals(id))
			.findFirst();
	}

	@Override
	public List<News> getAll() {
		return dataSource.getNews();
	}

	@Override
	public Optional<News> update(final News news) {
		final List<News> allNews = dataSource.getNews();
		int index = Utilities.getIndexById(allNews, news.getId());
		if (index != -1) {
			allNews.set(index, news);
			return Optional.of(news);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean delete(final long id) {
		final List<News> storage = dataSource.getNews();
		return storage.removeIf(n -> n.getId().equals(id));
	}
}