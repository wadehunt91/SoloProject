package com.wadehunt.mystorytime.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wadehunt.mystorytime.models.Comment;
import com.wadehunt.mystorytime.models.Story;
import com.wadehunt.mystorytime.models.User;
import com.wadehunt.mystorytime.services.CommentService;
import com.wadehunt.mystorytime.services.StoryService;
import com.wadehunt.mystorytime.services.UserService;

@Controller
@RequestMapping("/story")
public class StoryController {
	
	@Autowired
	private StoryService storyServ;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private CommentService commentServ;
	
	@GetMapping("/add")
	public String addStory(
			Model model,
			HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		else {
		Long userId = (Long) session.getAttribute("userId");
		User user = userServ.findById(userId);
		model.addAttribute("user", user);
		model.addAttribute("story", new Story());
		return "addStory.jsp";}
	}
	
	@PostMapping("/add")
	public String processAddStoryForm(
			@Valid
			@ModelAttribute("story") Story newStory,
			@ModelAttribute("user") User user,
			BindingResult result,
			Model model) {
			
			if(result.hasErrors()) {
				model.addAttribute("story", new Story());
				return "addStory.jsp";
			}
			else {
				storyServ.addStory(newStory, result);
				return "redirect:/dashboard/1";
			}
	}
	
	@GetMapping("/view/{id}")
	public String viewStory(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		else {
		model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
		model.addAttribute("story", storyServ.findById(id));
		model.addAttribute("comment", new Comment());
		model.addAttribute("currentComments", commentServ.findByStoryId(id));
		return "viewStory.jsp";
		}
	}
	
	@GetMapping("/edit/{id}")
	public String editStory(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session) {
		User user = userServ.findById((Long)session.getAttribute("userId"));
		Story story = storyServ.findById(id);
		if(user.getId() != story.getStoryAuthor().getId()) {
			return "redirect:/dashboard";
		}
		else {
			model.addAttribute("story", storyServ.findById(id));
			return "editStory.jsp";
		}
	}
	
	@PutMapping("/edit/{id}")
	public String updateStory(
			@PathVariable("id") Long id,
			Model model,
			@Valid
			@ModelAttribute("story") Story story,
			BindingResult result
			) {
		if(result.hasErrors()) {
			model.addAttribute("story", story );
			return "/editstory/{id}";
		}
		else {
			storyServ.save(story);
			return "redirect:/story/view/{id}";
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteStory(
			@PathVariable("id") Long id,
			HttpSession session) {
				User user = userServ.findById((Long)session.getAttribute("userId"));
				Story story = storyServ.findById(id);
				if(user.getId() != story.getStoryAuthor().getId()) {
					return "redirect:/dashboard/1";}
				else {
					storyServ.deleteStory(id);
					return "redirect:/dashboard/1";
				}
	}
}
