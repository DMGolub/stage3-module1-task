package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.domain.NewsModel;
import com.mjc.school.repository.utility.DataSource;
import com.mjc.school.repository.utility.Utilities;

import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {

	private final DataSource dataSource;

	public NewsRepositoryImpl() {
		dataSource = DataSource.getInstance();
	}

	public NewsRepositoryImpl(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public NewsModel create(final NewsModel news) {
		final List<NewsModel> allNews = dataSource.getNews();
		news.setId(Utilities.getMaxId(allNews) + 1);
		allNews.add(news);
		return news;
	}

	@Override
	public NewsModel readById(final long id) {
		final List<NewsModel> allNews = dataSource.getNews();
		return allNews.stream()
			.filter(n -> n.getId().equals(id))
			.findFirst().orElse(null);
	}

	@Override
	public List<NewsModel> readAll() {
		return dataSource.getNews();
	}

	@Override
	public NewsModel update(final NewsModel news) {
		final List<NewsModel> allNews = dataSource.getNews();
		int index = Utilities.getIndexById(allNews, news.getId());
		if (index != -1) {
			allNews.set(index, news);
			return news;
		} else {
			return null;
		}
	}

	@Override
	public Boolean delete(final long id) {
		final List<NewsModel> storage = dataSource.getNews();
		return storage.removeIf(n -> n.getId().equals(id));
	}
}