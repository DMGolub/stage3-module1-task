package com.mjc.school.command;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import com.mjc.school.service.exception.EntityNotFoundException;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.utility.NewsRequestInteractiveReader;

public class NewsAddCommand extends Command<NewsRequestDTO, NewsResponseDTO> {

	private static final String SUCCESS_MESSAGE = "Record saved successfully!";
	private static final String ERROR_MESSAGE = "We've got an error: ";

	public NewsAddCommand(final NewsController controller) {
		super(controller);
	}

	@Override
	public void execute() {
		try {
			NewsRequestDTO newNews = NewsRequestInteractiveReader.read(null);
			controller.save(newNews);
			System.out.println(SUCCESS_MESSAGE);
		} catch (EntityNotFoundException | ValidationException e) {
			System.out.println(ERROR_MESSAGE + e.getMessage());
		}
	}
}
