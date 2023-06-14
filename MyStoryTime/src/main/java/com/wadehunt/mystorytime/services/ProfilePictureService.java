package com.wadehunt.mystorytime.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadehunt.mystorytime.models.ProfilePicture;
import com.wadehunt.mystorytime.repositories.ProfilePictureRepository;

@Service
public class ProfilePictureService {
	@Autowired
	ProfilePictureRepository profilePicRepo;
	
	public ProfilePicture save(ProfilePicture picture) {
		return profilePicRepo.save(picture);
	}
	
	public ProfilePicture findByUserId(Long userId) {
		ProfilePicture profilePic = profilePicRepo.findByUserId(userId);
		if(profilePic != null) {
			return profilePic;
		}
		else {
			return null;
		}
	}
	
	public List<ProfilePicture> findAll() {
		return profilePicRepo.findAll();
	};
}
