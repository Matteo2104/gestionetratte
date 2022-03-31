package it.prova.gestionetratte.service.airbus;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusService {

	public List<Airbus> listAllElementsEager();

	public Airbus inserisciNuovo(Airbus airbus);

}
