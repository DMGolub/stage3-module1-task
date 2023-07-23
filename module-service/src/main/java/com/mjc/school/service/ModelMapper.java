package com.mjc.school.service;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.NewsResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ModelMapper {

	NewsResponseDto newsToResponseDto(NewsModel news);

	List<NewsResponseDto> newsListToDtoList(List<NewsModel> news);

	List<AuthorResponseDto> authorListToDtoList(List<AuthorModel> authors);

	@Mappings({
		@Mapping(target = "createDate", ignore = true),
		@Mapping(target = "lastUpdateDate", ignore = true)
	})
	NewsModel requestDtoToNews(NewsRequestDto request);
}