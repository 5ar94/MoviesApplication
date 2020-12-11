package com.petar.MoviesAppSpringBoot.repository;

import java.io.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import com.petar.MoviesAppSpringBoot.model.Movie;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {
	
}
