package com.petar.MoviesAppSpringBoot.repository;

import org.springframework.web.multipart.MultipartFile;

public interface RepositoryCustom {
	public void addImage(MultipartFile img,String name);
}
