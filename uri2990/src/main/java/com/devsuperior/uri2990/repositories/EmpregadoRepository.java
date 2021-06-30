package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

	@Query(nativeQuery = true, value = "SELECT E.cpf, E.enome, D.dnome "
			+"FROM empregados AS E " 
			+"INNER JOIN departamentos AS D on E.dnumero = D.dnumero " 
			+"WHERE E.cpf NOT IN( " 
			+	"SELECT E.cpf "
			+ 	"FROM empregados AS E " 
			+	"INNER JOIN trabalha AS T ON t.cpf_emp = E.cpf) "
			+"ORDER BY cpf")
	List<EmpregadoDeptProjection> sqlSearch();

	@Query(value = "SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) "
			+"FROM Empregado obj "
			+"WHERE obj.cpf NOT IN ( " 
			+	" SELECT obj.cpf "
			+ 	" FROM Empregado obj " 
			+	" INNER JOIN obj.projetosOndeTrabalha) "
			+"ORDER BY obj.cpf")
	List<EmpregadoDeptDTO> jpqlSearch();
}
