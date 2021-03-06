package kr.co.rockplae.web;

import javax.servlet.http.HttpSession;

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
import kr.co.rockplae.service.UserService;
import kr.co.rockplae.utils.HttpSessionUtils;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}

	@PostMapping("/loginProc")
	public String loginProc(String userId, String password, HttpSession session) {
//		User user = userRepository.findByUserId(userId);
		User user = userService.userfindByUserId(userId);
		if (user == null) {
			System.out.println("아이디 없음");
			return "redirect:/users/login_failed";
		}
		if (!user.confirmPassword(password)) {
			System.out.println("비밀번호 틀림");
			return "redirect:/users/login_failed";
		}
		System.out.println("로그인 성공!");
		session.setAttribute("sessionUser", user);

		return "redirect:/";
	}

	@GetMapping("/login_failed")
	public String loginFailed() {
		return "/user/login_failed";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/login_form")
	public String form() {
		return "/user/form";
	}

	@GetMapping("/profile")
	public String profile() {
		return "/user/profile";
	}

	@PostMapping("")
	public String create(User user) {
		System.out.println("User : " + user);
//		userRepository.save(user);
		userService.userCreate(user);
		
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users",userService.userFindAll());
		
		return "user/list";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		User user = userService.userFindById(loginUser.getId());
		model.addAttribute("user", user);
		
		return "/user/updateForm";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String password, User updateUser, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		User user = userService.userFindById(sessionUser.getId());
		user.update(updateUser);
		userService.userCreate(user);
		
		return "redirect:/users";
	}

}
