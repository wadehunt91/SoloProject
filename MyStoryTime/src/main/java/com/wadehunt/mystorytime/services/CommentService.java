package com.wadehunt.mystorytime.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadehunt.mystorytime.models.Comment;
import com.wadehunt.mystorytime.repositories.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepo;
	
	public List<Comment> findByStoryId(Long id){
		List<Comment> comments = commentRepo.findMostRecentComments(id);
		return comments;
	}
	
	public Comment save(Comment comment) {
		return commentRepo.save(comment);
	}
	
}
