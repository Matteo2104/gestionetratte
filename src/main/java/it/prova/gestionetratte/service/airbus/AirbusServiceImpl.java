package it.prova.gestionetratte.service.airbus;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService {
	
	@Autowired
	private AirbusRepository repository;
	
	@Override
	@Transactional
	public List<Airbus> listAllElementsEager() {
		return repository.findAllEager();
	}
	
	
	@Override
	@Transactional
	public Airbus inserisciNuovo(Airbus airbus) {
		return repository.save(airbus);
	}
	
	@Override
	@Transactional
	public Airbus caricaSingoloElementoConTratte(long id) {
		return repository.findByIdEager(id);
	}
	
	@Override
	@Transactional
	public Airbus caricaSingoloElemento(long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Airbus aggiorna(Airbus airbus) {
		return repository.save(airbus);
	}
}