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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petar.MoviesAppSpringBoot.model.Actor;
import com.petar.MoviesAppSpringBoot.model.Movie;
import com.petar.MoviesAppSpringBoot.repository.ActorsRepositoryImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ActorsController {
	
	@Autowired
	ActorsRepositoryImpl repo;
	
	@GetMapping("actors")
	@Produces("application/json")
	@ResponseBody
	public List<Actor> getActors(){
		return repo.findAllActors();
	}
	
	@PostMapping("actors")
	@ResponseBody
	public Actor addActor(@RequestParam(value="image")MultipartFile img, String json) {
		try {
			Actor actor = new ObjectMapper().readValue(json, Actor.class);
			repo.addImage(img, actor.getActImage());
			return repo.saveActor(actor);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("actors/{id}")
	@ResponseBody
	public Optional<Actor> getActor(@PathVariable int id) {
		return repo.findActorById(id);
	}
	
	@GetMapping("actors/{id}/movies")
	@ResponseBody
	public Set<Movie> getActorMovies(@PathVariable int id){
		return repo.getMovies(id);
	}
	
	@DeleteMapping("actors/{id}")
	@ResponseBody
	public void deleteActor(@PathVariable int id) {
		repo.deleteActorById(id);
	}
	
	@PutMapping("actors")
	@ResponseBody
	public Actor updateActor(@RequestParam(value = "image", required=false) MultipartFile img, String actor) {
		Actor act = null;
		try {
			act = new ObjectMapper().readValue(actor, Actor.class);
			if (img!=null) {
				repo.addImage(img, act.getActImage());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return repo.updateActor(act);
	}
	
	//treba da sredim return
	@GetMapping("actors/images/{name}")
	@ResponseBody
	@Produces(MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("name") String name){
		System.out.println(name);
		InputStream in;
		try {
			in = new FileInputStream(new File("src\\pictures\\actors\\"+name));
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
