package it.prova.gestionetratte.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.AirbusDTO;
import it.prova.gestionetratte.dto.TrattaDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.airbus.AirbusService;
import it.prova.gestionetratte.service.tratta.TrattaService;
import it.prova.gestionetratte.web.api.exception.AirbusNotEmptyException;
import it.prova.gestionetratte.web.api.exception.AirbusNotFoundException;
import it.prova.gestionetratte.web.api.exception.AirbusNotNullForInsertException;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertException;
import it.prova.gestionetratte.web.api.exception.TrattaNotFoundException;


@RestController
@RequestMapping("api/tratta")
public class TrattaController {
	@Autowired
	private TrattaService trattaService;
	
	@GetMapping
	public List<TrattaDTO> getAll() {
		return TrattaDTO.createTrattaDTOListFromModelList(trattaService.listAllElementsEager(), true);
	}
	
	
	@GetMapping("/{id}")
	public TrattaDTO findById(@PathVariable(value = "id", required = true) long id) {
		Tratta tratta = trattaService.caricaSingoloElementoConAirbus(id);

		if (tratta == null)
			throw new TrattaNotFoundException("Tratta not found con id: " + id);

		return TrattaDTO.buildTrattaDTOFromModel(tratta, true);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TrattaDTO createNew(@Valid @RequestBody TrattaDTO trattaInput) {
		//se mi viene inviato un id jpa lo interpreta come update ed a me (producer) non sta bene
		if(trattaInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		if (trattaInput.getAirbus() == null || trattaInput.getAirbus().getId() == null)
			throw new AirbusNotNullForInsertException("Non è ammesso inserire una tratta senza un airbus associato");
		
		Tratta trattaInserita = trattaService.inserisciNuovo(trattaInput.buildTrattaModel());
		
		//System.out.println(AirbusDTO.buildAirbusDTOFromModel(airbusInserito, false));
		
		return TrattaDTO.buildTrattaDTOFromModel(trattaInserita, false);
	}
	
	
	@PutMapping("/{id}")
	public TrattaDTO update(@Valid @RequestBody TrattaDTO trattaInput, @PathVariable(required = true) Long id) {
		Tratta tratta = trattaService.caricaSingoloElementoConAirbus(id);

		if (tratta == null)
			throw new TrattaNotFoundException("Tratta not found con id: " + id);

		trattaInput.setId(id);
		if (trattaInput.getAirbus() == null || trattaInput.getAirbus().getId() == null)
			trattaInput.setAirbus(AirbusDTO.buildAirbusDTOFromModel(tratta.getAirbus(), false));
		
		
		Tratta trattaAggiornata = trattaService.aggiorna(trattaInput.buildTrattaModel());
		return TrattaDTO.buildTrattaDTOFromModel(trattaAggiornata, false);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Tratta tratta = trattaService.caricaSingoloElemento(id);

		if (tratta == null)
			throw new TrattaNotFoundException("Tratta not found con id: " + id);
		
		trattaService.rimuovi(tratta);
	}
	
	/*
	@PostMapping("/search")
	public List<AirbusDTO> search(@RequestBody AirbusDTO example) {
		return AirbusDTO.createAirbusDTOListFromModelList(
				airbusService.findByExample(example.buildAirbusModel(), null, null, null).toList(), false);
	}
	*/
}
