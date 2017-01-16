package kr.co.rockplae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rockplae.domain.User;
import kr.co.rockplae.domain.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRespository;

	public User userfindByUserId(String userId) {
		return userRespository.findByUserId(userId);
	}

	public void userCreate(User user) {
		userRespository.save(user);
	}

	public List<User> userFindAll() {
		return (List<User>) userRespository.findAll();
	}

	public User userFindById(Long userId) {
		return userRespository.findOne(userId);
	}

}
