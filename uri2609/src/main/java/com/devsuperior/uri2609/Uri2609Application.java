package com.devsuperior.uri2609;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import com.devsuperior.uri2609.repositories.CategoryRepository;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("SQL");
		List<CategorySumProjection> searchRaiz = repository.sqlSearch();
		System.out.println("\n");
		List<CategorySumDTO> listDTO = searchRaiz.stream().map(x -> new CategorySumDTO(x)).collect(Collectors.toList());
		listDTO.forEach(x -> System.out.println(x.toString()));
		
		System.out.println("\n\n");
		
		System.out.println("JPQL");
		List<CategorySumDTO> searchJpql = repository.jpqlSearch();
		System.out.println("\n");
		searchJpql.forEach(x -> System.out.println(x.toString()));
		
	}
}
