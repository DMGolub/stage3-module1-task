package com.mjc.school.command;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.utility.ConsoleReader;

public class NewsDeleteCommand extends Command<NewsRequestDTO, NewsResponseDTO> {

	private static final String WELCOME_MESSAGE = "Enter news id:";
	private static final String SUCCESS_MESSAGE = "News deleted successfully";
	private static final String FAIL_MESSAGE = "Could not delete news";
	private static final String ERROR_MESSAGE = "We've got an error: ";

	public NewsDeleteCommand(final NewsController controller) {
		super(controller);
	}

	@Override
	public void execute() {
		final Long id = ConsoleReader.readPositiveLong(WELCOME_MESSAGE);
		try {
			boolean isDeleted = controller.delete(id);
			System.out.println(isDeleted ? SUCCESS_MESSAGE : FAIL_MESSAGE);
		} catch (ValidationException e) {
			System.out.println(ERROR_MESSAGE + e.getMessage());
		}
	}
}