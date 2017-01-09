package kr.co.rockplae.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String questionForm() {
		return "/qna/form";
	}

	@GetMapping("/{seq}/show")
	public String questionShow(@PathVariable Long seq, Question question, Model model) {
		question = questionRepository.findOne(seq);
		model.addAttribute("questions", question);
		return "/qna/show";
	}

	@PostMapping("")
	public String questionCreate(Question question) {
		System.out.println("Question : " + question);
		questionRepository.save(question);
		return "redirect:/questions/";
	}

	@GetMapping("/")
	public String questionList(Model model) {
		model.addAttribute("questions", questionRepository.findAll());
		return "index";
	}

}
