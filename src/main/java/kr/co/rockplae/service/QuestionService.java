package kr.co.rockplae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rockplae.domain.Question;
import kr.co.rockplae.domain.QuestionRepository;
import kr.co.rockplae.domain.User;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	public void questionCreate(Question question) {
		questionRepository.save(question);
	}

	public Question questionFindBySeq(Long seq) {
		return questionRepository.findOne(seq);
	}

	public void questionDelete(Long seq, User user) {
		Question question = questionFindBySeq(seq);
		if(question.isEqualWriter(user)) {
			question.delete(user);
			questionRepository.save(question);
		};
	}
	
}
