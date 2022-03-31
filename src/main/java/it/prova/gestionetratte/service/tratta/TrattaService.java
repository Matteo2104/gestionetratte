package it.prova.gestionetratte.service.tratta;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;

public interface TrattaService {

	public List<Tratta> listAllElementsEager();

	public Tratta inserisciNuovo(Tratta tratta);

	public Tratta caricaSingoloElementoConAirbus(long id);

	public Tratta caricaSingoloElemento(long id);

	public Tratta aggiorna(Tratta tratta);

	public void rimuovi(Tratta tratta);

}
