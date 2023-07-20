package com.mjc.school.service.utility;

import com.mjc.school.repository.domain.AuthorModel;
import com.mjc.school.repository.domain.NewsModel;
import com.mjc.school.service.dto.AuthorResponseDTO;
import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;
import org.modelmapper.ModelMapper;


public class Mapper {

	private static Mapper instance;
	private final ModelMapper modelMapper;

	private Mapper() {
		modelMapper = new ModelMapper();
	}

	public static Mapper getInstance() {
		if (instance == null) {
			instance = new Mapper();
		}
		return instance;
	}

	public NewsResponseDTO convertEntityToResponseDto(final NewsModel news) {
		return modelMapper.map(news, NewsResponseDTO.class);
	}

	public AuthorResponseDTO convertEntityToResponseDto(final AuthorModel author) {
		return modelMapper.map(author, AuthorResponseDTO.class);
	}

	public NewsModel convertRequestDtoToEntity(final NewsRequestDTO request) {
		return modelMapper.map(request, NewsModel.class);
	}
}