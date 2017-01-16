package kr.co.rockplae.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private long seq;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;

	private String title;

	private String contents;

	private LocalDateTime createDate;

	@OneToMany(mappedBy = "question")
	public List<Answer> answers;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_delete_user"))
	private User deleteUser;

	private LocalDateTime deleteDate;

	private Integer deleteFlag;

	public Question() {
	}

	public Question(User writer, String title, String contents, User deleteUser, Integer deleteFlag,
			List<Answer> answers) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
		this.deleteUser = deleteUser;
		this.deleteFlag = deleteFlag;
		this.answers = answers;
	}

	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH:mm:ss"));
	}

	@Override
	public String toString() {
		return "Question [seq=" + seq + ", writer=" + writer + ", title=" + title + ", contents=" + contents
				+ ", createDate=" + createDate + ", answers=" + answers + "]";
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isEqualWriter(User user) {
		return this.writer.confirmId(user);
	}

	public boolean isEmptyAnswer() {
		return this.answers.size() == 0;
	}

	public boolean isEqualAnswerWriter() {
		for (Answer answer : answers) {
			if (!answer.isEqualWriter(this.writer)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean delete(User user) {
		if (!isEqualWriter(user)) {
			return false;
		}
		if (!isEmptyAnswer()) {
			return isEqualAnswerWriter();
		}
		this.deleteUser = user;
		this.deleteDate = LocalDateTime.now();
		this.deleteFlag = 1;

		return true;
	}

}
