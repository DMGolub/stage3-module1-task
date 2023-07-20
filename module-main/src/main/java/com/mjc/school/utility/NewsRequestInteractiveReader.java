package com.mjc.school.utility;

import com.mjc.school.service.dto.NewsRequestDTO;

public class NewsRequestInteractiveReader {

	private static final String TITLE_MESSAGE = "Enter news title:";
	private static final String CONTENT_MESSAGE = "Enter news content:";
	private static final String AUTHOR_MESSAGE = "Enter author id (must be positive):";
	private static final int NEWS_TITLE_MIN_LENGTH = 5;
	private static final int NEWS_TITLE_MAX_LENGTH = 30;
	private static final int NEWS_CONTENT_MIN_LENGTH = 5;
	private static final int NEWS_CONTENT_MAX_LENGTH = 255;

	private NewsRequestInteractiveReader() {
		// empty. Hides default public constructor
	}

	public static NewsRequestDTO read(Long newsId) {
		String title = ConsoleReader.readText(
			TITLE_MESSAGE,
			NEWS_TITLE_MIN_LENGTH,
			NEWS_TITLE_MAX_LENGTH
		);
		String content = ConsoleReader.readText(
			CONTENT_MESSAGE,
			NEWS_CONTENT_MIN_LENGTH,
			NEWS_CONTENT_MAX_LENGTH
		);
		Long authorId = ConsoleReader.readPositiveLong(AUTHOR_MESSAGE);
		return new NewsRequestDTO(newsId, title, content, authorId);
	}
}