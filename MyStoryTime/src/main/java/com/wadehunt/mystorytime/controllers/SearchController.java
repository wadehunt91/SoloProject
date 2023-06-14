package com.wadehunt.mystorytime.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wadehunt.mystorytime.models.Story;
import com.wadehunt.mystorytime.services.ProfilePictureService;
import com.wadehunt.mystorytime.services.StoryService;
import com.wadehunt.mystorytime.services.StorySortingService;
import com.wadehunt.mystorytime.services.UserService;

@Controller
public class SearchController {
	
	@Autowired
	UserService userServ;
	
	@Autowired
	StorySortingService sortingServ;
	
	@Autowired
	StoryService storyServ;
	
	@Autowired 
	ProfilePictureService profilePicServ;
	
	
	@GetMapping("/search")
	public String openSearchPage(
			Model model,
			HttpSession session) {
		model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
		return "searchStoryQuery.jsp";
	}
	
	@GetMapping("/search/")
	public String searchStories(
			@RequestParam(value="q", required=false) String storyGenre,
			Model model,
			HttpSession session
			) {
		List<Story> stories = storyServ.findByStoryGenre(storyGenre);
		if(stories.isEmpty()) {
			stories = null;
		}
		model.addAttribute("stories", storyServ.findByStoryGenre(storyGenre));
		model.addAttribute("pictureUrl", "/profilepicture/story/${story.storyAuthor.id}");
		model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
		model.addAttribute("pic", profilePicServ.findAll());
		return "searchStoryQuery.jsp";
	}
}
