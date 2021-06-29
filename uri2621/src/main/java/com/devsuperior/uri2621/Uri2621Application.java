package com.devsuperior.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import com.devsuperior.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("SQL");
		List<ProductMinProjection> searchRaiz = repository.sqlSearch("p",10,20);
		System.out.println("\n");
		List<ProductMinDTO> listDTO = searchRaiz.stream().map(x -> new ProductMinDTO(x)).collect(Collectors.toList());
		listDTO.forEach(x -> System.out.println(x.toString()));
		
		System.out.println("\n\n");
		
		System.out.println("JPQL");
		List<ProductMinProjection> searchJpql = repository.jpqlSearch("p",10,20);
		System.out.println("\n");
		List<ProductMinDTO> listDTO1 = searchJpql.stream().map(x -> new ProductMinDTO(x)).collect(Collectors.toList());
		listDTO1.forEach(x -> System.out.println(x.toString()));
	}
}
