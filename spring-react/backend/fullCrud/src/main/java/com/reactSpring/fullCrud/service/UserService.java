package com.reactSpring.fullCrud.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactSpring.fullCrud.deo.UserDao;
import com.reactSpring.fullCrud.entity.User;
import com.reactSpring.fullCrud.exception.IncorrectPasswordException;
import com.reactSpring.fullCrud.exception.UserNotFoundException;
import com.reactSpring.fullCrud.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public UserDao saveUser(User user) {
		User savedUser = repo.save(user);
		UserDao daoUser = convertUserToUserDao(user);
		return daoUser;
	}

	public List<User> findAllUser() {
		List<User> users = repo.findAll();
		return users;
	}

	public Optional<User> findUserById(Long id) {
		Optional<User> user = repo.findById(id);
		return user;
	}

	public void deleteUserById(Long id) {
		repo.deleteById(id);
	}

	public UserDao matchUserPassword(String userId, String password) {
		User user = repo.findUserByUserId(userId);

		if (user != null) {
			if (user.getPassword().equals(password)) {
				UserDao newUser = convertUserToUserDao(user);
				return newUser;

			} else {

				throw new IncorrectPasswordException("Wrong password!");
			}

		} else {
			throw new UserNotFoundException("User with Id " + userId + " not found.");
		}
	}

	public UserDao convertUserToUserDao(User user) {
		UserDao daoUser = new UserDao();

		daoUser.setId(user.getId());
		daoUser.setName(user.getName());
		daoUser.setUserId(user.getUserId());
		daoUser.setBio(user.getBio());
		daoUser.setCity(user.getCity());
		if (user.getImage() == null) {
			daoUser.setImage("");
		} else {
			daoUser.setImage(Base64.getEncoder().encodeToString(user.getImage())); // String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		}
		daoUser.setDateOfbirth(user.getDateOfbirth());
		return daoUser;
	}

}
