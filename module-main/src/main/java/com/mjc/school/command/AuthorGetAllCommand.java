package com.mjc.school.command;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;

public class AuthorGetAllCommand extends Command<AuthorRequestDto, AuthorResponseDto> {

	private static final String WELCOME_MESSAGE = "All available authors:";

	public AuthorGetAllCommand(Controller<AuthorRequestDto, AuthorResponseDto> controller) {
		super(controller);
	}

	@Override
	public void execute() {
		System.out.println(WELCOME_MESSAGE);
		controller.readAll().forEach(a -> System.out.println(a.name() + ", id=" + a.id()));
	}
}