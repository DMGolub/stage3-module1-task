package com.mjc.school.command;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;

import java.util.List;

public class NewsGetAllCommand extends Command<NewsRequestDTO, NewsResponseDTO> {

	private static final String WELCOME_MESSAGE = "All available news:";

	public NewsGetAllCommand(final NewsController controller) {
		super(controller);
	}

	@Override
	public void execute() {
		System.out.println(WELCOME_MESSAGE);
		List<NewsResponseDTO> news = controller.readAll();
		news.forEach(System.out::println);
	}
}
