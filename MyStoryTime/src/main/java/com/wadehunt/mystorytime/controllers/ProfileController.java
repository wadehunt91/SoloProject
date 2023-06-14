package com.wadehunt.mystorytime.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wadehunt.mystorytime.models.ProfilePicture;
import com.wadehunt.mystorytime.models.User;
import com.wadehunt.mystorytime.services.ProfilePictureService;
import com.wadehunt.mystorytime.services.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private ProfilePictureService profilePicServ;
	
	@GetMapping("/{id}")
	public String profile(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session,
			@ModelAttribute("newUser") User newUser) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		else {
			model.addAttribute("picture", profilePicServ.findByUserId((Long)session.getAttribute("userId")));
			model.addAttribute("profilePicture", new ProfilePicture());
			model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
			return "profile.jsp";
		}
	}
	
	@PutMapping("/{id}")
	public String updateProfile(
			Model model,
			@Valid
			@ModelAttribute("user") User user,
			BindingResult result
			) {
		if(result.hasErrors()) {
			model.addAttribute("user", user );
			return "profile.jsp";
		}
		else {
			userServ.save(user);
			return "redirect:/dashboard";
		}
	}
	
}
