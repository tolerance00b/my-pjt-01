package kr.co.rockplae.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.rockplae.domain.Question;
import kr.co.rockplae.domain.QuestionRepository;
import kr.co.rockplae.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/form")
	public String questionForm(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		
		return "/qna/form";
	}

	@PostMapping("")
	public String questionCreate(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}

		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		
		return "redirect:/";
	}

	@GetMapping("/{seq}/show")
	public String questionShow(@PathVariable Long seq, Question question, Model model) {
		model.addAttribute("questions", questionRepository.findOne(seq));
		
		return "/qna/show";
	}
	
	@GetMapping("/{seq}/form")
	public String questionUpdateForm(@PathVariable Long seq, Model model) {
		model.addAttribute("questions", questionRepository.findOne(seq));
		
		return "/qna/updateForm";
	}
	
	@PutMapping("/{seq}")
	public String questionUpdate(@PathVariable Long seq, Model model, String title, String contents) {
		Question question = questionRepository.findOne(seq);
		question.update(title, contents);
		questionRepository.save(question);
		return "redirect:/questions/" + seq + "/show";
	}
	
	@DeleteMapping("/{seq}")
	public String questionDelete(@PathVariable Long seq) {
		questionRepository.delete(seq);
		
		return "redirect:/";
	}
	
}
