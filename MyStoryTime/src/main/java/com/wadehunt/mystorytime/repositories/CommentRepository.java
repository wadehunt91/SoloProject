package com.wadehunt.mystorytime.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wadehunt.mystorytime.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByStoryId(Long id);
	
	@Query(value="SELECT * FROM a_good_book.comment WHERE story_id=?1 ORDER BY created_at DESC", nativeQuery=true)
	List<Comment> findMostRecentComments(Long id);
}
