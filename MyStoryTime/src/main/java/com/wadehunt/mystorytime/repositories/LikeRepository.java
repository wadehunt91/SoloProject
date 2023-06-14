package com.wadehunt.mystorytime.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wadehunt.mystorytime.models.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	List<Like> findByStoryId(Long id);
	
	@Query(value="SELECT * FROM a_good_book.likes WHERE story_id=?1 AND user_id=?2 ORDER BY created_at DESC", nativeQuery=true)
	Optional<Like> findStoryLikes(Long storyId, Long userId);
//	
//	@Query(value="DELETE * FROM a_good_book.likes WHERE story_id=?1 AND user_id=?2 ORDER BY created_at DESC", nativeQuery=true)
//	void deleteByIds(Long storyId, Long userId);
}
