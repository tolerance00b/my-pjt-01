package kr.co.rockplae.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("")
	public String home(String name, String age, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "index";
	}
}
