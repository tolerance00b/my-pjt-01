package kr.co.rockplae.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.rockplae.domain.Answer;
import kr.co.rockplae.domain.AnswerRepository;
import kr.co.rockplae.domain.Question;
import kr.co.rockplae.domain.QuestionRepository;
import kr.co.rockplae.domain.User;
import kr.co.rockplae.utils.HttpSessionUtils;

@Controller
@RequestMapping("/questions/{seq}/answers")
public class AnswerController {
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public String answerCreate(@PathVariable Long seq, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(seq);
		Answer answer = new Answer(sessionUser, question, contents);
		answerRepository.save(answer);
		
		return "redirect:/questions/" + seq + "/show";
	}
	
	@DeleteMapping("/{id}")
	public String answerDelete(@PathVariable Long seq, @PathVariable Long id, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		
		return "redirect:/questions/" + seq + "/show";
	}
	
	
}
