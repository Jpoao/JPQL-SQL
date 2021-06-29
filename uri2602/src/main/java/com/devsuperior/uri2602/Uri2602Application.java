package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner{

	@Autowired
	private CustomerRepository customerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		System.out.println("SQL");
		List<CustomerMinProjection> searchRaiz = customerRepository.sqlSearch("rs");
		System.out.println("\n");
		List<CustomerMinDTO> listDTO = searchRaiz.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());
		listDTO.forEach(x -> System.out.println(x.toString()));
		
		System.out.println("\n\n");
		
		System.out.println("JPQL");
		List<CustomerMinProjection> searchJpql = customerRepository.jpqlSearch("rs");
		System.out.println("\n");
		List<CustomerMinDTO> listDTO1 = searchJpql.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());
		listDTO1.forEach(x -> System.out.println(x.toString()));
		
	}
}
