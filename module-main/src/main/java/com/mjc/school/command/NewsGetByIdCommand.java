package com.mjc.school.command;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.utility.ConsoleReader;

public class NewsGetByIdCommand extends Command<NewsRequestDTO, NewsResponseDTO> {

	private static final String WELCOME_MESSAGE = "Enter news id:";
	private static final String ERROR_MESSAGE = "We've ot an error: ";

	public NewsGetByIdCommand(final NewsController controller) {
		super(controller);
	}

	@Override
	public void execute() {
		final Long id = ConsoleReader.readPositiveLong(WELCOME_MESSAGE);
		try {
			NewsResponseDTO news = controller.getById(id);
			System.out.println(news);
		} catch (EntityNotFoundException e) {
			System.out.println(ERROR_MESSAGE + e.getMessage());
		}
	}
}