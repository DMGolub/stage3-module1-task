package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.domain.News;
import com.mjc.school.repository.exception.EntityNotFoundException;
import com.mjc.school.repository.utility.DataSource;
import com.mjc.school.repository.utility.Utilities;

import java.util.List;

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
	public News getById(final long id) throws EntityNotFoundException {
		final List<News> allNews = dataSource.getNews();
		return allNews.stream()
			.filter(n -> n.getId().equals(id))
			.findFirst()
			.orElseThrow(() -> new EntityNotFoundException("Can not find news by id = " + id));
	}

	@Override
	public List<News> getAll() {
		return dataSource.getNews();
	}

	@Override
	public News update(final News news) throws EntityNotFoundException {
		final List<News> allNews = dataSource.getNews();
		int index = Utilities.getIndexById(allNews, news.getId());
		if (index != -1) {
			allNews.set(index, news);
			return news;
		} else {
			throw new EntityNotFoundException("Can not find news by id = " + news.getId());
		}
	}

	@Override
	public boolean delete(final long id) {
		final List<News> storage = dataSource.getNews();
		return storage.removeIf(n -> n.getId().equals(id));
	}
}