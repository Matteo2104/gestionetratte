package it.prova.gestionetratte.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long>, PagingAndSortingRepository<Tratta, Long>, JpaSpecificationExecutor<Tratta> {

}
