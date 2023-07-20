package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsRequestDTO;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class NewsControllerImplTest {

	private final NewsService newsService = mock(NewsService.class);
	private final NewsController newsController = new NewsControllerImpl(newsService);

	@Test
	void save_shouldInvokeServiceSaveMethod_whenInvoked() {
		final NewsRequestDTO request = new NewsRequestDTO(null, "Title", "Content", 1L);

		newsController.create(request);
		verify(newsService, times(1)).create(request);
	}

	@Test
	void getAll_shouldInvokeServiceGetAllMethod_whenInvoked() {
		newsController.readAll();
		verify(newsService, times(1)).readAll();
	}

	@Test
	void getById_shouldInvokeServiceGetByIdMethod_whenInvoked() {
		final long id = 1L;

		newsController.readById(id);

		verify(newsService, times(1)).readById(id);
	}

	@Test
	void update_shouldInvokeServiceUpdateMethod_whenInvoked() {
		final NewsRequestDTO request = new NewsRequestDTO(15L, "Title", "Content", 1L);

		newsController.update(request);
		verify(newsService, times(1)).update(request);
	}

	@Test
	void delete_shouldInvokeServiceDelete_whenInvoked() {
		final long id = 1L;

		newsController.delete(id);

		verify(newsService, times(1)).delete(id);
	}
}