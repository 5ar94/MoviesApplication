package com.petar.MoviesAppSpringBoot.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.multipart.MultipartFile;

import com.petar.MoviesAppSpringBoot.model.Actor;
import com.petar.MoviesAppSpringBoot.model.Movie;

public class ActorsRepositoryImpl implements RepositoryCustom {
	
	@Autowired
	@Lazy
	ActorsRepository repo;
	
	public Optional<Actor> findActorById(int id) {
		return repo.findById(id);
	}
	
	public List<Actor> findAllActors() {
		return repo.findAll();
	}
	
	public Actor saveActor(Actor actor) {
		return repo.save(actor);
	}
	
	public Actor updateActor(Actor actor) {
		Actor updateActor = repo.findById(actor.getActId()).get();
		updateActor.setActFirstName(actor.getActFirstName());
		updateActor.setActImage(actor.getActImage());
		updateActor.setActLastName(actor.getActLastName());
		updateActor.setActBiography(actor.getActBiography());
		updateActor.setMovies(actor.getMovies());
		return repo.save(updateActor);
	}
	
	public void deleteActorById(int id) {
		 repo.deleteById(id);
	}
	
	public Set<Movie> getMovies(int id){
		Actor actor = repo.findById(id).get();
		return actor.getMovies();
	}

	@Override
	public void addImage(MultipartFile img, String name) {
		File destination = new File("C:\\Users\\Petar\\Desktop\\MovieApp\\MoviesApplication\\MoviesAppSpringBoot\\src\\pictures\\actors\\"+name);
		try {
			img.transferTo(destination);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}

}
