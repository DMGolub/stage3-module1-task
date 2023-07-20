package com.mjc.school.controller.impl;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.service.AuthorService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AuthorControllerImplTest {

	private final AuthorService authorService = mock(AuthorService.class);
	private final AuthorController authorController = new AuthorControllerImpl(authorService);

	@Test
	void getAll_shouldInvokeServiceGetAllMethod_whenInvoked() {
		authorController.getAll();
		verify(authorService, times(1)).getAll();
	}
}