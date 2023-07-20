package com.mjc.school.command;

import com.mjc.school.controller.Controller;

public abstract class Command<T, R> {

	protected final Controller<T, R> controller;

	protected Command(final Controller<T, R> controller) {
		this.controller = controller;
	}

	public abstract void execute();
}