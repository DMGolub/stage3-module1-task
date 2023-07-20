package com.mjc.school.command;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.service.dto.AuthorRequestDTO;
import com.mjc.school.service.dto.AuthorResponseDTO;

import java.util.List;

public class AuthorGetAllCommand extends Command<AuthorRequestDTO, AuthorResponseDTO> {

	private static final String WELCOME_MESSAGE = "All available authors:";

	public AuthorGetAllCommand(AuthorController controller) {
		super(controller);
	}

	@Override
	public void execute() {
		List<AuthorResponseDTO> authors = controller.getAll();
		System.out.println(WELCOME_MESSAGE);
		authors.forEach(a -> System.out.println(a.getName() + ", id=" + a.getId()));
	}
}