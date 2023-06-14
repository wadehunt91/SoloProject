package com.wadehunt.mystorytime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wadehunt.mystorytime.models.ProfilePicture;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long>{
	ProfilePicture findByUserId(Long userId);
}
