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

	@OneToMany(mappedBy = "question")
	public List<Answer> answers;

//	@ManyToOne
//	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_delete_user"))
//	private Long deleteUser;
	
	private LocalDateTime deleteDate;

	private Integer deleteFlag;

	@Override
	public String toString() {
		return "Question [seq=" + seq + ", writer=" + writer + ", title=" + title + ", contents=" + contents
				+ ", createDate=" + createDate + ", answers=" + answers + "]";
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isEqualWriter(Long user) {
		if (user == null) {
			return false;
		}
		return this.writer.equals(user);
	}

	public boolean isEmptyAnswer() {
		return this.answers.isEmpty();
	}

	//
	public boolean isEqualAnswerWriter() {
		for (Answer answer : answers) {
			if (!answer.isEqualWriter(this.writer)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean delete(Long user) {
		if(!isEqualWriter(user)) {
			return false;
		}
		
		if(!isEmptyAnswer()) {
			return isEqualAnswerWriter();
		}
		
//		this.deleteUser = user;
		this.deleteDate = LocalDateTime.now();
		this.deleteFlag = 1;
		
		return true;
	}

}
