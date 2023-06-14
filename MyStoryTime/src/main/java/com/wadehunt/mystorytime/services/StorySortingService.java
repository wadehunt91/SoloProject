package com.wadehunt.mystorytime.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wadehunt.mystorytime.models.Story;
import com.wadehunt.mystorytime.repositories.SortingRepository;

@Service
@Transactional
public class StorySortingService {
	
	@Autowired SortingRepository sortingRepo;
	
	private static final int PAGE_SIZE = 5;
	
	public Page<Story> storiesPerPage(int pageNumber){
		PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.Direction.DESC, "createdAt");
		Page<Story> stories = sortingRepo.findAll(pageRequest);
		return stories;
	}
//	
//	public Page<Story> genreStoriesPerPage(int pageNumber, String storyGenre){
//		PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.Direction.DESC, "createdAt");
//		List<Story> stories = sortingRepo.findStoriesByGenre(storyGenre);
//		return stories;
//	}
	
}
