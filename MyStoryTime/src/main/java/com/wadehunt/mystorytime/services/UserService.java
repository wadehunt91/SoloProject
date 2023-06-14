package com.wadehunt.mystorytime.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.wadehunt.mystorytime.models.LoginUser;
import com.wadehunt.mystorytime.models.User;
import com.wadehunt.mystorytime.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public List<User> findAll(){
		List <User> allUsers = userRepo.findAll();
		return allUsers;
	}
	
	public User findById(Long id) {
		Optional <User> logInAttempt = userRepo.findById(id);
		return logInAttempt.orElseGet(()->null);
	}
	
	public User register(
			User newUser, 
			BindingResult result) {
		Optional<User> optUser = userRepo.findByEmail(newUser.getEmail());
		if(optUser.isPresent()) {
			result.rejectValue("email", null, "An account with that email already exists.");}
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("password", null,"Passwords need to match");
		}
		if(result.hasErrors()) {
			return null;
		}
		else {
		String hashedPw = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPw);
	}
		return userRepo.save(newUser);
	}
	
	public User loginUser(
			LoginUser loginUser, 
			BindingResult result) {
		Optional<User> optUser = userRepo.findByEmail(loginUser.getEmail());
		if(!optUser.isPresent()) {
			result.rejectValue("email", null, "User or password incorrect");
			return null;
		}
		User user = optUser.get();
		if(!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())){
			result.rejectValue("password", null, "User or password incorrect");
		}
		if(result.hasErrors()) {
			return null;
		}
		return user;
	}

	public User save(User u) {
		return userRepo.save(u);
	}
}
