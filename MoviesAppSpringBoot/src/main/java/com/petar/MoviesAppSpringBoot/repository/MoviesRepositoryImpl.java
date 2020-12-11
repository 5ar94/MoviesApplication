package com.petar.MoviesAppSpringBoot.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.petar.MoviesAppSpringBoot.model.Actor;
import com.petar.MoviesAppSpringBoot.model.Movie;

@Component
public class MoviesRepositoryImpl implements RepositoryCustom {

	@Autowired
	MoviesRepository repo;

	@Autowired
	@Lazy
	ActorsRepositoryImpl actor;

	public Optional<Movie> findMovieById(int id) {
		return repo.findById(id);
	}

	public List<Movie> findAllMovies() {
		return repo.findAll();
	}

	public Movie saveMovie(Movie movie) {
		return repo.save(movie);
	}

	public void addActorsToMovies(int[] actors, int movId) {
		if (actors.length != 0) {
			Movie movie = this.findMovieById(movId).get();
			for (int i = 0; i < actors.length; i++) {
				System.out.println("u metodi sam");
				Actor act = actor.findActorById(actors[i]).get();
				act.getMovies().add(movie);
				movie.getActors().add(act);
			}
			this.saveMovie(movie);
		}
	}

	public Movie updateMovie(Movie movie) {
		Movie updateMovie = repo.findById(movie.getMovId()).get();
		updateMovie.setActors(movie.getActors());
		updateMovie.setMovCategory(movie.getMovCategory());
		updateMovie.setMovDescription(movie.getMovDescription());
		updateMovie.setMovImage(movie.getMovImage());
		updateMovie.setMovName(movie.getMovName());
		return repo.save(updateMovie);
	}

	public void deleteMovieById(int id) {
		repo.deleteById(id);
	}
	
	public Set<Actor> getActors(int id){
		Movie movie = repo.findById(id).get();
		return movie.getActors();
	}

	@Override
	public void addImage(MultipartFile img, String name) {
		File destination = new File(
				"C:\\Users\\Petar\\Desktop\\MovieApp\\MoviesApplication\\MoviesAppSpringBoot\\src\\pictures\\movies\\"
						+ name);
		try {
			img.transferTo(destination);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

	}

}
