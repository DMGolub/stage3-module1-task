package com.mjc.school.command;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.utility.NewsRequestInteractiveReader;

public class NewsAddCommand extends Command<NewsRequestDto, NewsResponseDto> {

	private static final String SUCCESS_MESSAGE = "Record saved successfully!";
	private static final String ERROR_MESSAGE = "We've got an error: ";

	public NewsAddCommand(final Controller<NewsRequestDto, NewsResponseDto> controller) {
		super(controller);
	}

	@Override
	public void execute() {
		try {
			NewsRequestDto newNews = NewsRequestInteractiveReader.read(null);
			controller.create(newNews);
			System.out.println(SUCCESS_MESSAGE);
		} catch (EntityNotFoundException | ValidationException e) {
			System.out.println(ERROR_MESSAGE + e.getMessage());
		}
	}
}
