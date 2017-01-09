package kr.co.rockplae.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.rockplae.domain.Question;
import kr.co.rockplae.domain.QuestionRepository;

@Controller
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;

	@PostMapping("/question")
	public String Question(Question question) {
		System.out.println("Question : " + question);
		questionRepository.save(question);
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String question_list(Model model) {
		model.addAttribute("question_list", questionRepository.findAll());
		return "index";
	}
}
