package it.prova.gestionetratte.service.airbus;

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
	
	@Override
	@Transactional
	public void rimuovi(Airbus airbus) {
		repository.delete(airbus);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Airbus> findByExample(Airbus example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Airbus> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();
			
		

			if (StringUtils.isNotEmpty(example.getCodice()))
				predicates.add(cb.like(cb.upper(root.get("codice")), "%" + example.getCodice().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getDescrizione()))
				predicates.add(cb.like(cb.upper(root.get("descrizione")), "%" + example.getDescrizione().toUpperCase() + "%"));

			
			if (example.getDataInizioServizio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInizioServizio"), example.getDataInizioServizio()));
			
			if (example.getNumeroPasseggeri() > 0)
				predicates.add(cb.greaterThanOrEqualTo(root.get("numeroPasseggeri"), example.getNumeroPasseggeri()));

			
			
		
			
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
