package kr.co.rockplae.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private long seq;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;

	private String title;
	
	@Lob
	private String contents;

	private LocalDateTime createDate;

	public Question() {
	}

	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}

	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH:mm:ss"));
	}

	@Override
	public String toString() {
		return "Question [seq=" + seq + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

}
