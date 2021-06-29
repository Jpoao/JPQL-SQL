package com.devsuperior.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(nativeQuery = true ,value = "SELECT C.name, SUM(P.amount) "
			+ "FROM categories AS C INNER JOIN products AS P "
			+ "ON P.id_categories = C.id "
			+ "GROUP BY C.name "
			+ " ORDER BY C.name ASC")
			List<CategorySumProjection> sqlSearch();
	
	@Query(value = "SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(obj.category.name, SUM(obj.amount)) "
			+ "FROM Product obj "
			+ "GROUP BY obj.category.name "
			+ "ORDER BY obj.category.name ASC")
	List<CategorySumDTO> jpqlSearch();
}
