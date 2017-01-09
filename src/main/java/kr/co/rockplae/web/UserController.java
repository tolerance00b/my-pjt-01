package kr.co.rockplae.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.rockplae.domain.User;
import kr.co.rockplae.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@PostMapping("")
	public String create(User user) {
		System.out.println("User : " + user);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "/user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updateUser) {
		User user = userRepository.findOne(id);
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/users";
	}
	

}
