package it.prova.gestionetratte.service.airbus;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusService {

	public List<Airbus> listAllElementsEager();

	public Airbus inserisciNuovo(Airbus airbus);

	public Airbus caricaSingoloElementoConTratte(long id);
	
	public Airbus caricaSingoloElemento(long id);

	public Airbus aggiorna(Airbus airbus);

	public void rimuovi(Airbus airbus);

	public Page<Airbus> findByExample(Airbus example, Integer pageNo, Integer pageSize, String sortBy);


}
