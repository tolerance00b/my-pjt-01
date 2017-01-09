package kr.co.rockplae.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private long seq;

	@Column(nullable = false)
	private String writer;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String contents;

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Question [seq=" + seq + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}

}
