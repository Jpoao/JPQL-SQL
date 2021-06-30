package com.devsuperior.uri2737.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
	
	@Query(nativeQuery = true ,value = "SELECT name, customers_number AS customersNumber FROM lawyers " 
			+ "where (customers_number = (SELECT Max(customers_number) FROM lawyers) OR customers_number = "
			+ "(SELECT Min(customers_number) FROM lawyers)) GROUP BY name, customers_number "
			+ "UNION ALL "
			+ "SELECT 'avarage', ROUND(AVG(customers_number),0) FROM lawyers")
			List<LawyerMinProjection> sqlSearch();
}
