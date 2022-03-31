package it.prova.gestionetratte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long>, PagingAndSortingRepository<Tratta, Long>, JpaSpecificationExecutor<Tratta> {
	@Query("from Tratta t left join fetch t.airbus")
	public List<Tratta> findAllEager();
	
	@Query("from Tratta t left join fetch t.airbus a where t.id = ?1")
	public Tratta findByIdEager(long id);
}
