package com.mjc.school.controller;

import java.util.List;

public interface Controller<T, R> {

	R save(T t);

	List<R> getAll();

	R getById(Long id);

	R update(T t);

	boolean delete(Long id);
}