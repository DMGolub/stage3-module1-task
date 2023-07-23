package com.mjc.school.command;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.utility.ConsoleReader;

public class NewsGetByIdCommand extends Command<NewsRequestDto, NewsResponseDto> {

	private static final String WELCOME_MESSAGE = "Enter news id:";
	private static final String ERROR_MESSAGE = "We've ot an error: ";

	public NewsGetByIdCommand(final Controller<NewsRequestDto, NewsResponseDto> controller) {
		super(controller);
	}

	@Override
	public void execute() {
		final Long id = ConsoleReader.readPositiveLong(WELCOME_MESSAGE);
		try {
			NewsResponseDto news = controller.readById(id);
			System.out.println(news);
		} catch (EntityNotFoundException e) {
			System.out.println(ERROR_MESSAGE + e.getMessage());
		}
	}
}