package com.mjc.school.repository.utility;

import com.mjc.school.repository.domain.News;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

	private static DataSource instance;
	private List<News> news;

	private DataSource() {
		// FIXME read data from file
		news = new ArrayList<>();
	}

	public static DataSource getInstance() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}

	public List<News> getNews() {
		return news;
	}
}