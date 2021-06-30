package com.devsuperior.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("SQL");
		List<EmpregadoDeptProjection> searchRaiz = repository.sqlSearch();
		System.out.println("\n");
		List<EmpregadoDeptDTO> listDTO = searchRaiz.stream().map(x -> new EmpregadoDeptDTO(x)).collect(Collectors.toList());
		listDTO.forEach(x -> System.out.println(x.toString()));
		
		System.out.println("\n\n");
		
		System.out.println("JPQL");
		List<EmpregadoDeptDTO> searchJpql = repository.jpqlSearch();
		System.out.println("\n");
		searchJpql.forEach(x -> System.out.println(x.toString()));
		
	}
}
