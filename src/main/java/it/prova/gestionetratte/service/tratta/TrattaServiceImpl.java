package it.prova.gestionetratte.service.tratta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.TrattaRepository;

@Service
public class TrattaServiceImpl implements TrattaService {
	
	@Autowired
	private TrattaRepository repository;
	
	@Override
	@Transactional
	public List<Tratta> listAllElementsEager() {
		return repository.findAllEager();
	}
	
	@Override
	@Transactional
	public Tratta inserisciNuovo(Tratta tratta) {
		return repository.save(tratta);
	}
	
	@Override
	@Transactional
	public Tratta caricaSingoloElementoConAirbus(long id) {
		return repository.findByIdEager(id);
	}
	
	@Override
	@Transactional
	public Tratta caricaSingoloElemento(long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Tratta aggiorna(Tratta tratta) {
		return repository.save(tratta);
	}
	
	@Override
	@Transactional
	public void rimuovi(Tratta tratta) {
		repository.delete(tratta);
	}
}
