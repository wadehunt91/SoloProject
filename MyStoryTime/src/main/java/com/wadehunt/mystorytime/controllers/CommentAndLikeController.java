package com.wadehunt.mystorytime.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wadehunt.mystorytime.models.Comment;
import com.wadehunt.mystorytime.models.Like;
import com.wadehunt.mystorytime.models.User;
import com.wadehunt.mystorytime.services.CommentService;
import com.wadehunt.mystorytime.services.LikeService;
import com.wadehunt.mystorytime.services.StoryService;
import com.wadehunt.mystorytime.services.UserService;

@Controller
public class CommentAndLikeController {
	@Autowired
	private StoryService storyServ;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private CommentService commentServ;
	
	@Autowired
	private LikeService likeServ;
	
	@PostMapping("/story/{storyId}/newComment")
	public String processCommentForm(
			@PathVariable("storyId") Long storyId,
			@Valid
			@ModelAttribute("comment") Comment newComment,
			Model model,
			BindingResult result,
			HttpSession session) {
		newComment.setStory(storyServ.findById(storyId));
		newComment.setCommentUser(userServ.findById((Long) session.getAttribute("userId")));
		commentServ.save(newComment);
		return "redirect:/story/view/" + storyId;
	}
	
	@PostMapping("/story/{storyId}/newLike")
	public String processLike(
			@PathVariable("storyId") Long storyId,
			@Valid
			@ModelAttribute("like") Like like,
			Model model,
			BindingResult result,
			HttpSession session) {
			User user = userServ.findById((Long) session.getAttribute("userId"));
			like.setStory(storyServ.findById(storyId));
			like.setLikeUser(userServ.findById((Long) session.getAttribute("userId")));
			likeServ.findAndChangeLike(storyId, user.getId() , like);
				return "redirect:/dashboard/1";
	}
}
