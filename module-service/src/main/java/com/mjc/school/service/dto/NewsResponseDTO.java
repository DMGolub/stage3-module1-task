package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class NewsResponseDTO {

	private Long id;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime lastUpdateDate;
	private Long authorId;

	public NewsResponseDTO() {
		// Empty. Used by modelMapper.
	}

	public NewsResponseDTO(
		final Long id,
		final String title,
		final String content,
		final LocalDateTime createDate,
		final LocalDateTime lastUpdateDate,
		final Long authorId
	) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
		this.authorId = authorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(final LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(final Long authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "News{" +
			"id=" + id +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", createDate=" + createDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
			", lastUpdateDate=" + lastUpdateDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
			", authorId=" + authorId +
			'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final NewsResponseDTO newsResponseDTO = (NewsResponseDTO) o;
		if (!Objects.equals(id, newsResponseDTO.id)) {
			return false;
		}
		if (!Objects.equals(title, newsResponseDTO.title)) {
			return false;
		}
		if (!Objects.equals(content, newsResponseDTO.content)) {
			return false;
		}
		if (!Objects.equals(createDate, newsResponseDTO.createDate)) {
			return false;
		}
		if (!Objects.equals(lastUpdateDate, newsResponseDTO.lastUpdateDate)) {
			return false;
		}
		return Objects.equals(authorId, newsResponseDTO.authorId);
	}

	@Override
	public int hashCode() {
		return 37;
	}
}