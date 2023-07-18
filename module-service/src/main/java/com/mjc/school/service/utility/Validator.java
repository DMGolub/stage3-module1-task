package com.mjc.school.service.utility;

import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.exception.ValidationException;

public class Validator {

	private static final String NEWS_TITLE_NAME = "news title";
	private static final String NEWS_CONTENT_NAME = "news content";
	private static final String AUTHOR_ID = "author id";
	private static final int NEWS_TITLE_MIN_LENGTH = 5;
	private static final int NEWS_TITLE_MAX_LENGTH = 30;
	private static final int NEWS_CONTENT_MIN_LENGTH = 5;
	private static final int NEWS_CONTENT_MAX_LENGTH = 255;

	private Validator() {
		// Empty. Hiding default public constructor.
	}

	public static void validatePositive(long number, String name) {
		if (number < 1) {
			throw new ValidationException(String.format("%s must be positive", name));
		}
	}

	public static void validateNotNull(Object obj, String name) {
		if (obj == null) {
			throw new ValidationException(String.format("%s can not be null", name));
		}
	}

	public static void validateRange(int number, String name, int rangeBegin, int rangeEnd) {
		if (number < rangeBegin || number > rangeEnd) {
			throw new ValidationException(
				String.format("%s value must be from %d to %d", name, rangeBegin, rangeEnd));
		}
	}

	public static void validateNewsRequestDTO(NewsRequestDTO request) {
		validateRange(
			request.title().length(),
			NEWS_TITLE_NAME,
			NEWS_TITLE_MIN_LENGTH,
			NEWS_TITLE_MAX_LENGTH
		);
		validateRange(
			request.content().length(),
			NEWS_CONTENT_NAME,
			NEWS_CONTENT_MIN_LENGTH,
			NEWS_CONTENT_MAX_LENGTH
		);
		validateNotNull(request.authorId(), AUTHOR_ID);
		validatePositive(request.authorId(), AUTHOR_ID);
	}
}