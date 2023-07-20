package com.mjc.school.controller;

import java.util.List;

public interface Controller<T, R> {

	R create(final T t);

	List<R> readAll();

	R readById(final Long id);

	R update(final T t);

	Boolean delete(final Long id);
}