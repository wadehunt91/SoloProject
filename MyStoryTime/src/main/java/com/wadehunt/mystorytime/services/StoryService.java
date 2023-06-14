 package com.wadehunt.mystorytime.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.wadehunt.mystorytime.models.Story;
import com.wadehunt.mystorytime.models.User;
import com.wadehunt.mystorytime.repositories.SortingRepository;
import com.wadehunt.mystorytime.repositories.StoryRepository;

@Service
public class StoryService {
	
	@Autowired
	StoryRepository storyRepo;
	
	@Autowired
	SortingRepository sortingRepo;
	
	public List<Story> findAll(){
		List<Story> allStories = storyRepo.findAll();
		return allStories;
	}
	
	public Story addStory(Story story, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		return storyRepo.save(story);
	}
	
	public Story findById(Long id) {
		@SuppressWarnings("deprecation")
		Story story = storyRepo.getById(id);
		return story;
	}
	
	public Story save(Story story) {
		return storyRepo.save(story);
	}
	
	public void deleteStory(Long id) {
		storyRepo.deleteById(id);
	}
	
	public List<Story> findByStoryAuthor(User user) {
		List<Story> story = storyRepo.findByStoryAuthor(user);
		return story;
	}
	
	public List<Story> findByStoryGenre(String storyGenre) {
		List<Story> story = sortingRepo.findStoriesByGenre(storyGenre);
		return story;
	}
}
