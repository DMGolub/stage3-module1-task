package com.mjc.school.conversation;

import com.mjc.school.command.AuthorGetAllCommand;
import com.mjc.school.command.Command;
import com.mjc.school.command.NewsAddCommand;
import com.mjc.school.command.NewsDeleteCommand;
import com.mjc.school.command.NewsGetAllCommand;
import com.mjc.school.command.NewsGetByIdCommand;
import com.mjc.school.command.NewsUpdateCommand;
import com.mjc.school.controller.Controller;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.utility.ConsoleReader;

import java.util.Map;

import static com.mjc.school.conversation.Operation.ADD_NEWS;
import static com.mjc.school.conversation.Operation.DELETE_NEWS_BY_ID;
import static com.mjc.school.conversation.Operation.GET_ALL_AUTHORS;
import static com.mjc.school.conversation.Operation.GET_ALL_NEWS;
import static com.mjc.school.conversation.Operation.GET_NEWS_BY_ID;
import static com.mjc.school.conversation.Operation.UPDATE_NEWS_BY_ID;

public class Conversation {

	private static final String MAIN_MENU_HEADER = "Main menu:";
	private static final String ENTER_COMMAND_MESSAGE = "Please enter command number...";
	private static final String NO_COMMAND_MESSAGE = "No such command, please try again";
	private static final String ERROR_MESSAGE = "We've got an error. Please try something else";

	private final Controller<AuthorRequestDto, AuthorResponseDto> authorController;
	private final Controller<NewsRequestDto, NewsResponseDto> newsController;
	private Map<Integer, Command<?, ?>> commands;

	public Conversation(
		final Controller<AuthorRequestDto, AuthorResponseDto> authorController,
		final Controller<NewsRequestDto, NewsResponseDto> newsController
	) {
		this.newsController = newsController;
		this.authorController = authorController;
		initCommands();
	}

	public void run() {
		conversation: while (true) {
			printMainMenu();
			int command = ConsoleReader.readPositiveLong(ENTER_COMMAND_MESSAGE).intValue();
			try {
				switch (command) {
					case 1 -> commands.get(1).execute();
					case 2 -> commands.get(2).execute();
					case 3 -> commands.get(3).execute();
					case 4 -> commands.get(4).execute();
					case 5 -> commands.get(5).execute();
					case 6 -> commands.get(6).execute();
					case 7 -> {
						break conversation;
					}
					default -> System.out.println(NO_COMMAND_MESSAGE);
				}
			} catch (Exception e) {
				System.out.println(ERROR_MESSAGE);
			}
		}
	}

	private void initCommands() {
		commands = Map.of(
			ADD_NEWS.getNumber(), new NewsAddCommand(newsController),
			GET_ALL_NEWS.getNumber(), new NewsGetAllCommand(newsController),
			GET_ALL_AUTHORS.getNumber(), new AuthorGetAllCommand(authorController),
			GET_NEWS_BY_ID.getNumber(), new NewsGetByIdCommand(newsController),
			UPDATE_NEWS_BY_ID.getNumber(), new NewsUpdateCommand(newsController),
			DELETE_NEWS_BY_ID.getNumber(), new NewsDeleteCommand(newsController)
		);
	}

	private void printMainMenu() {
		System.out.println(MAIN_MENU_HEADER);
		for (Operation operation : Operation.values()) {
			System.out.println(operation.getNumber() + ". " + operation.getOperation());
		}
	}
}