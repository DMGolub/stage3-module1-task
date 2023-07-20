package com.mjc.school.repository.utility;

import com.mjc.school.repository.domain.AuthorModel;
import com.mjc.school.repository.domain.NewsModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class InitialDataGenerator {

	private static final String NEWS_FILE_NAME = "/news.txt";
	private static final String CONTENT_FILE_NAME = "/content.txt";
	private static final String AUTHOR_FILE_NAME = "/author.txt";
	private static final int YEAR = 2023;
	private static final long NEWS_AMOUNT = 20;

	private static InitialDataGenerator instance;

	private final List<String> titles;
	private final List<String> content;
	private final List<AuthorModel> authors;
	final Random random;

	private InitialDataGenerator() {
		random = new Random();
		titles = readLinesFromFile(NEWS_FILE_NAME);
		content = readLinesFromFile(CONTENT_FILE_NAME);
		authors = convertToAuthors(readLinesFromFile(AUTHOR_FILE_NAME));
	}

	private List<String> readLinesFromFile(final String fileName) {
		List<String> lines = null;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
			Objects.requireNonNull(this.getClass().getResourceAsStream(fileName))))
		) {
			lines = reader.lines().toList();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return lines;
	}

	public static InitialDataGenerator getInstance() {
		if (instance == null) {
			instance = new InitialDataGenerator();
		}
		return instance;
	}

	public List<AuthorModel> getAuthors() {
		return authors;
	}

	public List<NewsModel> getNews() {
		List<NewsModel> allNews = new ArrayList<>();
		for (long i = 1; i <= NEWS_AMOUNT; i++) {
			LocalDateTime date = getRandomDate();
			allNews.add(new NewsModel(
				i,								// id
				getRandomTitle(), 				// title
				getRandomContent(), 			// content
				date,							// createDate
				date,							// lastUpdateDate
				getRandomAuthorId()				// authorId
			));
		}
		return allNews;
	}

	private List<AuthorModel> convertToAuthors(List<String> authors) {
		List<AuthorModel> result = new ArrayList<>();
		for (String author : authors) {
			String[] values = author.split(" ", 2);
			result.add(new AuthorModel(
				Long.parseLong(values[0]),
				values[1]
			));
		}
		return result;
	}

	private Long getRandomAuthorId() {
		return authors.get(random.nextInt(0, authors.size())).getId();
	}

	private String getRandomTitle() {
		return titles.get(random.nextInt(0, titles.size()));
	}

	private String getRandomContent() {
		return content.get(random.nextInt(0, content.size()));
	}

	private LocalDateTime getRandomDate() {
		return LocalDateTime.of(
			YEAR,									// year
			random.nextInt(1, 7),		// month
			random.nextInt(1, 29), 	// day
			random.nextInt(0, 24), 	// hour
			random.nextInt(0, 60)	 	// minute
		);
	}
}