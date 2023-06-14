package com.wadehunt.mystorytime.controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wadehunt.mystorytime.models.ProfilePicture;
import com.wadehunt.mystorytime.models.User;
import com.wadehunt.mystorytime.services.ProfilePictureService;
import com.wadehunt.mystorytime.services.StoryService;
import com.wadehunt.mystorytime.services.UserService;

@Controller
@RequestMapping("/profilepicture")
public class ImageController {
	
	@Autowired
	UserService userServ;
	
	@Autowired
	ProfilePictureService profilePicServ;
	
	@Autowired
	StoryService storyServ;
	
	@PostMapping("/{id}")
	public String uploadImage(
			@PathVariable("id") Long id,
			@RequestParam("file")MultipartFile file,
			Model model,
			HttpSession session) {
		User currentUser = userServ.findById(id);
		try {
			 model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
			 ProfilePicture optImage = profilePicServ.findByUserId((Long)session.getAttribute("userId"));
			 if(optImage == null || optImage.getData() == null) {
				ProfilePicture image = new ProfilePicture();
				image.setName(file.getOriginalFilename());
	            image.setData(file.getBytes());
	            image.setUserId(currentUser.getId());
	            profilePicServ.save(image);
	            return "redirect:/dashboard";}
			 else {
				 optImage.setName(file.getOriginalFilename());
				 optImage.setData(file.getBytes());
				 optImage.setUserId(currentUser.getId());
				 profilePicServ.save(optImage);
				 return "redirect:/profile/{id}";
			 }
		}
		catch (IOException e) {
            return "Failed to upload the image.";
	}
	}
	
	@GetMapping("/{userId}")
	public String displayImage(
			@PathVariable Long id, 
			Model model,
			HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
			ProfilePicture picture = profilePicServ.findByUserId((Long)session.getAttribute("userId"));
			model.addAttribute("picture", picture);
			return "profile.jsp";
	}
		
	@GetMapping("/show/{userId}")
	public void showImage(
			@PathVariable Long userId,
			HttpServletResponse response,
			HttpSession session
			) throws IOException {
		
		ProfilePicture image = profilePicServ.findByUserId((Long)session.getAttribute("userId"));
		
		if (image != null && image.getData() != null) {
	        OutputStream outputStream = response.getOutputStream();
	        outputStream.write(image.getData());
	        outputStream.flush();
	    }
	}
    
	@GetMapping("/story/{userId}")
	public void showStoryImage(
			@PathVariable Long userId,
			HttpServletResponse response,
			HttpSession session
			) throws IOException {
		ProfilePicture image = profilePicServ.findByUserId(userId);
		if (image != null && image.getData() != null) {
	        OutputStream outputStream = response.getOutputStream();
	        outputStream.write(image.getData());
	        outputStream.flush();
	    }
	}
}
