package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utility.DataSource;
import com.mjc.school.repository.utility.Utilities;

import java.util.List;
import java.util.NoSuchElementException;

public class NewsRepository implements Repository<NewsModel> {

	private final DataSource dataSource;

	public NewsRepository() {
		dataSource = DataSource.getInstance();
	}

	NewsRepository(final DataSource dataSource) {
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
	public NewsModel readById(final Long newsId) throws NoSuchElementException {
		return dataSource.getNews().stream()
			.filter(news -> newsId.equals(news.getId()))
			.findFirst()
			.get();
	}

	@Override
	public List<NewsModel> readAll() {
		return dataSource.getNews();
	}

	@Override
	public NewsModel update(final NewsModel updatedNews) throws NoSuchElementException {
		NewsModel news = readById(updatedNews.getId());
		news.setTitle(updatedNews.getTitle());
		news.setContent(updatedNews.getContent());
		news.setLastUpdateDate(updatedNews.getLastUpdateDate());
		news.setAuthorId(updatedNews.getAuthorId());
		return news;
	}

	@Override
	public Boolean deleteById(final Long id) {
		return dataSource.getNews().removeIf(n -> n.getId().equals(id));
	}

	public Boolean isExistById(final Long newsId) {
		return dataSource.getNews().stream().anyMatch(news -> newsId.equals(news.getId()));
	}
}