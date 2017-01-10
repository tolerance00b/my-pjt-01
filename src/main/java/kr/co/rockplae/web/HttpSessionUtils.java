package kr.co.rockplae.web;

import javax.servlet.http.HttpSession;

import kr.co.rockplae.domain.User;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionUser";

	public static final boolean isLoginUser(HttpSession session) {
		Object sessionUser = session.getAttribute(USER_SESSION_KEY);
		if (sessionUser == null) {
			return false;
		}
		return true;
	}

	public static User getUserFromSession(HttpSession session) {
		if (!isLoginUser(session)) {
			return null;
		}

		return (User) session.getAttribute(USER_SESSION_KEY);
	}

}
