package com.wadehunt.mystorytime.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wadehunt.mystorytime.models.Story;

public interface SortingRepository extends PagingAndSortingRepository<Story, Long>{
	
	@Query(value="SELECT * FROM a_good_book.story WHERE story_genre = ?1 ORDER BY created_at DESC", nativeQuery=true)
	List<Story> findStoriesByGenre(String storyGenre);
}
