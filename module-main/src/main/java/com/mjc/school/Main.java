package com.mjc.school;

import com.mjc.school.controller.Controller;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.conversation.Conversation;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;

public class Main {

	public static void main(String[] args) {
		Controller<NewsRequestDto, NewsResponseDto> newsController = new NewsController();
		Controller<AuthorRequestDto, AuthorResponseDto> authorController = new AuthorController();
		Conversation conversation = new Conversation(authorController, newsController);
		conversation.run();
	}
}