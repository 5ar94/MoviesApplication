package com.petar.MoviesAppSpringBoot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petar.MoviesAppSpringBoot.model.Actor;
import com.petar.MoviesAppSpringBoot.model.Movie;
import com.petar.MoviesAppSpringBoot.repository.MoviesRepository;
import com.petar.MoviesAppSpringBoot.repository.MoviesRepositoryImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MoviesController {

	@Autowired
	MoviesRepositoryImpl repo;

	@GetMapping("movies")
	@Produces("application/json")
	@ResponseBody
	public List<Movie> getMovies() {
		return repo.findAllMovies();
	}

	@PostMapping("movies")
	@ResponseBody
	public Movie addMovie(@RequestParam(value = "image") MultipartFile img, String json,
			@RequestParam(value = "array") int[] array) {
		try {
			Movie movie = new ObjectMapper().readValue(json, Movie.class);
			repo.addImage(img, movie.getMovImage());
			repo.saveMovie(movie);

			repo.addActorsToMovies(array, movie.getMovId());
			return movie;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("movies/{id}")
	@ResponseBody
	public Optional<Movie> getMovie(@PathVariable int id) {
		return repo.findMovieById(id);
	}

	@DeleteMapping("movies/{id}")
	@ResponseBody
	public void deleteMovie(@PathVariable int id) {
		repo.deleteMovieById(id);
	}

	@GetMapping("movies/{id}/actors")
	@ResponseBody
	public Set<Actor> getMovieActors(@PathVariable int id) {
		return repo.getActors(id);
	}

	@PutMapping("movies")
	@ResponseBody
	public Movie updateMovie(@RequestParam(value = "image", required=false) MultipartFile img, String movie) {
		Movie mov = null;
		try {
			mov = new ObjectMapper().readValue(movie, Movie.class);
			if (img!=null) {
				repo.addImage(img, mov.getMovImage());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return repo.updateMovie(mov);
	}

	// Treba da sredim return i proverim filenotfoundexc
	@GetMapping("movies/images/{name}")
	@ResponseBody
	@Produces(MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("name") String name) {
		System.out.println(name);
		InputStream in;
		try {
			in = new FileInputStream(new File("src\\pictures\\movies\\" + name));
			return in.readAllBytes();
		} catch (FileNotFoundException e) {
			if (!name.equals("undefined")) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
