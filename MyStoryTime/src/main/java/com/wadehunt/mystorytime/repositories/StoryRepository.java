package com.wadehunt.mystorytime.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wadehunt.mystorytime.models.Story;
import com.wadehunt.mystorytime.models.User;

public interface StoryRepository extends JpaRepository<Story, Long>{
	List<Story> findAll();
	Optional<Story> findByStoryTitle(String title);
	Optional<Story> findById(Long id);
	List<Story> findByStoryGenre(String genre);
	List<Story> findByStoryAuthor(User author);
	
	
	
}