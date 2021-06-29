package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;

public interface MovieRepository extends JpaRepository<Movie, Long>  {

	@Query(nativeQuery = true ,value = "SELECT movies.id, movies.name FROM movies "
			+ "INNER JOIN genres ON movies.id_genres = genres.id "
			+ "where genres.description = :genreName")
	List<MovieMinProjection> sqlSearch(String genreName);
	
	@Query(value = "SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name) FROM Movie obj "
			+ "where obj.genre.description = :genreName")
	List<MovieMinProjection> jpqlSearch(String genreName);
}
