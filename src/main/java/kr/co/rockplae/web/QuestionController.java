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
import kr.co.rockplae.domain.User;
import kr.co.rockplae.service.QuestionService;
import kr.co.rockplae.utils.HttpSessionUtils;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionService questionService ;
	
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
		questionService.questionCreate(newQuestion);

		return "redirect:/";
	}

	@GetMapping("/{seq}/show")
	public String questionShow(@PathVariable Long seq, Question question, Model model) {
		model.addAttribute("questions", questionService.questionFindBySeq(seq));

		return "/qna/show";
	}

	@GetMapping("/{seq}/form")
	public String questionUpdateForm(@PathVariable Long seq, Model model) {
		model.addAttribute("questions", questionService.questionFindBySeq(seq));

		return "/qna/updateForm";
	}

	@PutMapping("/{seq}")
	public String questionUpdate(@PathVariable Long seq, Model model, String title, String contents) {
		Question question = questionService.questionFindBySeq(seq);
		question.update(title, contents);
		questionService.questionCreate(question);
		return "redirect:/questions/" + seq + "/show";
	}

	@DeleteMapping("/{seq}")
	public String questionDelete(@PathVariable Long seq, HttpSession session, Question question) throws Exception {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		questionService.questionDelete(seq, sessionUser.getId());
		
		return "redirect:/";
	}
	
}
