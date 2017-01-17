package kr.co.rockplae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rockplae.domain.Answer;
import kr.co.rockplae.domain.AnswerRepository;
import kr.co.rockplae.domain.User;

@Service
public class AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	
	public void answerCreate(Answer answer) {
		answerRepository.save(answer);
	}
	
	public void answerDelete(Long seq, User user) {
		
		
	}
	
}
