package com.petar.MoviesAppSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petar.MoviesAppSpringBoot.model.Actor;

@Repository
public interface ActorsRepository extends JpaRepository<Actor, Integer> {

}
