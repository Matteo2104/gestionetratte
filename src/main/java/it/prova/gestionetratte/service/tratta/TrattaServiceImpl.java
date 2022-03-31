package it.prova.gestionetratte.service.tratta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
	
	@Override
	@Transactional(readOnly = true)
	public Page<Tratta> findByExample(Tratta example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Tratta> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
		

			if (StringUtils.isNotEmpty(example.getCodice()))
				predicates.add(cb.like(cb.upper(root.get("codice")), "%" + example.getCodice().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getDescrizione()))
				predicates.add(cb.like(cb.upper(root.get("descrizione")), "%" + example.getDescrizione().toUpperCase() + "%"));

			
			if (example.getData() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("data"), example.getData()));
			
			if (example.getOraDecollo() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("oraDecollo"), example.getOraDecollo()));
			
			if (example.getOraAtterraggio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("oraAtterraggio"), example.getOraAtterraggio()));
			
			
			if (example.getStato() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("stato"), example.getStato()));

			if (example.getAirbus() != null && example.getAirbus().getId() != null)
				predicates.add(cb.equal(root.get("airbus"), example.getAirbus().getId()));
			
			
		
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));

		};
		
		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}
}
