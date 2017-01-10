package kr.co.rockplae.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "uid", length = 20, nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	private String email;

	public long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean confirmId(Long newId) {
		if (newId == null) {
			return false;
		}
		
		return newId.equals(id);
	}

	public boolean confirmPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}

		return newPassword.equals(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void update(User updateUser) {
		if (!confirmPassword(updateUser.getPassword())) {
			throw new IllegalStateException("비밀번호 틀림");
		}
		this.name = updateUser.name;
		this.email = updateUser.email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}

}
