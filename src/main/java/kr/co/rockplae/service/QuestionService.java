package kr.co.rockplae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rockplae.domain.Question;
import kr.co.rockplae.domain.QuestionRepository;

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

	public void questionDelete(Long seq, Long user) {
		Question question = questionFindBySeq(seq);
		question.delete(user);
		questionRepository.save(question);
	}
	
}
