package kr.co.rockplae.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;

	private String contents;

	private LocalDateTime createDate;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_delete_user"))
	private User deleteUser;

	private LocalDateTime deleteDate;

	private Integer deleteFlag;

	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH:mm:ss"));
	}

	public Answer() {

	}

	public Answer(User writer, Question question, String contents) {
		this.writer = writer;
		this.question = question;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", question=" + question + ", contents=" + contents
				+ ", createDate=" + createDate + ", deleteUser=" + deleteUser + ", deleteDate=" + deleteDate
				+ ", deleteFlag=" + deleteFlag + "]";
	}

	public boolean isEqualWriter(User user) {
		return this.writer.confirmId(user);
	}

	public boolean delete(User user) {
		if (!isEqualWriter(user)) {
			return false;
		}
		this.deleteUser = user;
		this.deleteDate = LocalDateTime.now();
		this.deleteFlag = 1;

		return true;
	}
}
