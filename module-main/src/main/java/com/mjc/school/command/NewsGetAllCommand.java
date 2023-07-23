package com.mjc.school.command;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;

public class NewsGetAllCommand extends Command<NewsRequestDto, NewsResponseDto> {

	private static final String WELCOME_MESSAGE = "All available news:";

	public NewsGetAllCommand(final Controller<NewsRequestDto, NewsResponseDto> controller) {
		super(controller);
	}

	@Override
	public void execute() {
		System.out.println(WELCOME_MESSAGE);
		controller.readAll().forEach(System.out::println);
	}
}
