package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner{
	
	@Autowired
	private MovieRepository movieRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("SQL");
		List<MovieMinProjection> searchRaiz = movieRepository.sqlSearch("Action");
		System.out.println("\n");
		List<MovieMinDTO> listDTO = searchRaiz.stream().map(x -> new MovieMinDTO(x)).collect(Collectors.toList());
		listDTO.forEach(x -> System.out.println(x.toString()));
		
		System.out.println("\n\n");
		
		System.out.println("JPQL");
		List<MovieMinProjection> searchJpql = movieRepository.jpqlSearch("Action");
		System.out.println("\n");
		List<MovieMinDTO> listDTO1 = searchJpql.stream().map(x -> new MovieMinDTO(x)).collect(Collectors.toList());
		listDTO1.forEach(x -> System.out.println(x.toString()));
		
	}
}
