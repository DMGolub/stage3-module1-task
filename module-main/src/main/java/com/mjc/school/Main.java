package com.mjc.school;

import com.mjc.school.command.AuthorGetAllCommand;
import com.mjc.school.command.NewsUpdateCommand;
import com.mjc.school.controller.AuthorController;
import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.impl.AuthorControllerImpl;
import com.mjc.school.controller.impl.NewsControllerImpl;
import com.mjc.school.command.NewsAddCommand;
import com.mjc.school.command.Command;
import com.mjc.school.utility.ConsoleReader;
import com.mjc.school.command.NewsDeleteCommand;
import com.mjc.school.command.NewsGetAllCommand;
import com.mjc.school.command.NewsGetByIdCommand;
import java.util.Map;
import java.util.StringJoiner;

public class Main {

	private static final String MAIN_MENU;
	private static final String ENTER_COMMAND_MESSAGE = "Please enter command number...";
	private static final String NO_COMMAND_MESSAGE = "No such command, please try again";
	private static final String ERROR_MESSAGE = "We've got an error. Please try something else";

	static {
		MAIN_MENU = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
			.add("|--------------------------|")
			.add("| Main menu:               |")
			.add("| 1. Add new news record   |")
			.add("| 2. Show all news         |")
			.add("| 3. Show all authors      |")
			.add("| 4. Get news record by id |")
			.add("| 5. Update news by id     |")
			.add("| 6. Delete news by id     |")
			.add("| 7. Quit program          |")
			.add("|__________________________|")
			.toString();
	}

	public static void main(String[] args) {
		NewsController newsController = new NewsControllerImpl();
		AuthorController authorController = new AuthorControllerImpl();

		Map<Integer, Command<?, ?>> commands = Map.of(
			1, new NewsAddCommand(newsController),
			2, new NewsGetAllCommand(newsController),
			3, new AuthorGetAllCommand(authorController),
			4, new NewsGetByIdCommand(newsController),
			5, new NewsUpdateCommand(newsController),
			6, new NewsDeleteCommand(newsController)
		);

		conversation: while (true) {
			System.out.print(MAIN_MENU);
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
}