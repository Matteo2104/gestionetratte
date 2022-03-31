package it.prova.gestionetratte.service.tratta;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaService {

	public List<Tratta> listAllElementsEager();

	public Tratta inserisciNuovo(Tratta tratta);

	public Tratta caricaSingoloElementoConAirbus(long id);

	public Tratta caricaSingoloElemento(long id);

	public Tratta aggiorna(Tratta tratta);

	public void rimuovi(Tratta tratta);

	public Page<Tratta> findByExample(Tratta example, Integer pageNo, Integer pageSize, String sortBy);

}
