package it.prova.gestionetratte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>, PagingAndSortingRepository<Airbus, Long>, JpaSpecificationExecutor<Airbus> {
	@Query("from Airbus a left join fetch a.tratte t")
	public List<Airbus> findAllEager();
}
