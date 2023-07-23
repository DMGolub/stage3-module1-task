package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.impl.AuthorService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AuthorControllerTest {

	private final Service<AuthorRequestDto, AuthorResponseDto> authorService = mock(AuthorService.class);
	private final Controller<AuthorRequestDto, AuthorResponseDto> authorController =
		new AuthorController(authorService);

	@Test
	void getAll_shouldInvokeServiceGetAllMethod_whenInvoked() {
		authorController.readAll();
		verify(authorService, times(1)).readAll();
	}
}