package com.wadehunt.mystorytime.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadehunt.mystorytime.models.Like;
import com.wadehunt.mystorytime.repositories.LikeRepository;

@Service
public class LikeService {
	
	@Autowired
	LikeRepository likeRepo;
	
	public List<Like> findAll(){
		return likeRepo.findAll();
	};
	
	public List<Like> findByStoryId(Long id){
		List<Like> likes = likeRepo.findByStoryId(id);
		return likes;
	}
	
	public Like save(Like like) {
		return likeRepo.save(like);
	}
	
	public void delete(Like like) {
		likeRepo.delete(like);
	}
	
	public void findAndChangeLike(Long storyId, Long userId, Like like) {
		Optional<Like> isLiked = likeRepo.findStoryLikes(storyId, userId);
		if(isLiked.isEmpty()) {
			likeRepo.save(like);
		}
		else {
			likeRepo.deleteById(isLiked.get().getId());
		}
	}
}
