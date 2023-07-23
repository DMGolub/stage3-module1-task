package com.mjc.school.conversation;

public enum Operation {
	ADD_NEWS(1, "Add new news record"),
	GET_ALL_NEWS(2, "Show all news"),
	GET_ALL_AUTHORS(3, "Show all authors"),
	GET_NEWS_BY_ID(4, "Get news record by id"),
	UPDATE_NEWS_BY_ID(5, "Update news by id"),
	DELETE_NEWS_BY_ID(6, "Delete news by id"),
	QUIT_PROGRAM(7, "Quit program");

	private Operation(final Integer operationNumber, final String operation) {
		this.operationNumber = operationNumber;
		this.operation = operation;
	}

	private final Integer operationNumber;
	private final String operation;

	public Integer getNumber() {
		return operationNumber;
	}

	public String getOperation() {
		return operation;
	}
}