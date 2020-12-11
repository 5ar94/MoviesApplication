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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mov_id")
	private int movId;
	@Column(name="mov_name")
	private String movName;
	@Column(name="mov_description")
	private String movDescription;
	@Column(name="mov_category")
	private String movCategory;
	@Column(name="mov_image")
	private String movImage;
	
	@JsonBackReference
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="movies_actors",
			joinColumns = {@JoinColumn(name="mov_id")},
			inverseJoinColumns = {@JoinColumn(name="act_id")})
	private Set<Actor> actors = new HashSet<>();

	public Movie() {
		
	}

	public Movie(int movId, String movName, String movDescription, String movCategory, String movImage,
			Set<Actor> actors) {
		this.movId = movId;
		this.movName = movName;
		this.movDescription = movDescription;
		this.movCategory = movCategory;
		this.movImage = movImage;
		this.actors = actors;
	}

	public Movie(String movName, String movDescription, String movCategory, String movImage) {
		this.movName = movName;
		this.movDescription = movDescription;
		this.movCategory = movCategory;
		this.movImage = movImage;
	}

	@Override
	public String toString() {
		return "Movie [movId=" + movId + ", movName=" + movName + ", movDescription=" + movDescription
				+ ", movCategory=" + movCategory + ", movImage=" + movImage + "]";
	}

	public int getMovId() {
		return movId;
	}

	public void setMovId(int movId) {
		this.movId = movId;
	}

	public String getMovName() {
		return movName;
	}

	public void setMovName(String movName) {
		this.movName = movName;
	}

	public String getMovDescription() {
		return movDescription;
	}

	public void setMovDescription(String movDescription) {
		this.movDescription = movDescription;
	}

	public String getMovCategory() {
		return movCategory;
	}

	public void setMovCategory(String movCategory) {
		this.movCategory = movCategory;
	}

	public String getMovImage() {
		return movImage;
	}

	public void setMovImage(String movImage) {
		this.movImage = movImage;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	
	

}
