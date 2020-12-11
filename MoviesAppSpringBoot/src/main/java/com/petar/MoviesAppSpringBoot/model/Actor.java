package com.petar.MoviesAppSpringBoot.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="actors")
public class Actor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int actId;
	@Column(name="act_first_name")
	private String actFirstName;
	@Column(name="act_last_name")
	private String actLastName;
	@Column(name="act_biography")
	private String actBiography;
	@Column(name="act_image")
	private String actImage;
	
	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors")
	private Set<Movie> movies = new HashSet<>();

	public Actor() {
		
	}
	
	
	
	public Actor(int actId, String actFirstName, String actLastName, String actBiography, String actImage,
			Set<Movie> movies) {
		this.actId = actId;
		this.actFirstName = actFirstName;
		this.actLastName = actLastName;
		this.actBiography = actBiography;
		this.actImage = actImage;
		this.movies = movies;
	}



	public Actor(String actFirstName, String actLastName, String actBiography, String actImage) {
		this.actFirstName = actFirstName;
		this.actLastName = actLastName;
		this.actBiography = actBiography;
		this.actImage = actImage;
	}

	@Override
	public String toString() {
		return "Actor [actId=" + actId + ", actFirstName=" + actFirstName + ", actLastName=" + actLastName
				+ ", actBiography=" + actBiography + ", actImage=" + actImage + "]";
	}

	public int getActId() {
		return actId;
	}

	public void setActId(int actId) {
		this.actId = actId;
	}

	public String getActFirstName() {
		return actFirstName;
	}

	public void setActFirstName(String actFirstName) {
		this.actFirstName = actFirstName;
	}

	public String getActLastName() {
		return actLastName;
	}

	public void setActLastName(String actLastName) {
		this.actLastName = actLastName;
	}

	public String getActBiography() {
		return actBiography;
	}

	public void setActBiography(String actBiography) {
		this.actBiography = actBiography;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public String getActImage() {
		return actImage;
	}

	public void setActImage(String actImage) {
		this.actImage = actImage;
	}
	
	

}
