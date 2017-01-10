package kr.co.rockplae.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

public class Answer {
	@Id
	@GeneratedValue
	private Long seq;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = " fk_answer_writer"))
	private User writer;
	
	@Lob
	private String contents;

	private LocalDateTime createDate;

	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH:mm:ss"));
	}
}
