package com.mjc.school.service.dto;

import java.util.Objects;

public class NewsRequestDTO {

	private Long id;
	private String title;
	private String content;
	private Long authorId;

	public NewsRequestDTO(
		final Long id,
		final String title,
		final String content,
		final Long authorId
	) {
		this.id = id;
		this.title = title;
		this.content = content;
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
		final NewsRequestDTO that = (NewsRequestDTO) o;
		if (!Objects.equals(id, that.id)) {
			return false;
		}
		if (!Objects.equals(title, that.title)) {
			return false;
		}
		if (!Objects.equals(content, that.content)) {
			return false;
		}
		return Objects.equals(authorId, that.authorId);
	}

	@Override
	public int hashCode() {
		return 37;
	}
}