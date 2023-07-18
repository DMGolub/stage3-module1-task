package com.mjc.school.repository.utility;

import com.mjc.school.repository.domain.Author;
import com.mjc.school.repository.domain.News;

import java.util.List;

public class DataSource {

	private static DataSource instance;
	private final List<Author> authors;
	private final List<News> news;


	private DataSource() {
		authors = InitialDataGenerator.getInstance().getAuthors();
		news = InitialDataGenerator.getInstance().getNews();
	}

	public static DataSource getInstance() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}

	public List<Author> getAuthors() {
		return authors;
	}
	public List<News> getNews() {
		return news;
	}
}