package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "SELECT P.name FROM "
			+ "products AS P INNER JOIN providers AS pr ON P.id_providers = pr.id "
			+ "WHERE UPPER(pr.name) LIKE UPPER(CONCAT(:name,'%'))"
			+ " AND p.amount BETWEEN :min AND :max")
	List<ProductMinProjection> sqlSearch(String name, Integer min, Integer max);

	@Query(value = "SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) "
			+ "FROM Product obj "
			+ "WHERE UPPER(obj.provider.name) LIKE UPPER(CONCAT(:name,'%'))"
			+ " AND obj.amount BETWEEN :min AND :max")
	List<ProductMinProjection> jpqlSearch(String name, Integer min, Integer max);

}
