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
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.service.airbus.AirbusService;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertException;
import it.prova.gestionetratte.web.api.exception.AirbusNotFoundException;


@RestController
@RequestMapping("api/airbus")
public class AirbusController {
	
	@Autowired
	private AirbusService airbusService;
	
	@GetMapping
	public List<AirbusDTO> getAll() {
		return AirbusDTO.createAirbusDTOListFromModelList(airbusService.listAllElementsEager(), true);
	}
	
	@GetMapping("/{id}")
	public AirbusDTO findById(@PathVariable(value = "id", required = true) long id) {
		Airbus airbus = airbusService.caricaSingoloElementoConTratte(id);

		if (airbus == null)
			throw new AirbusNotFoundException("Airbus not found con id: " + id);

		return AirbusDTO.buildAirbusDTOFromModel(airbus, true);
	}

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AirbusDTO createNew(@Valid @RequestBody AirbusDTO airbusInput) {
		//se mi viene inviato un id jpa lo interpreta come update ed a me (producer) non sta bene
		if(airbusInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		Airbus airbusInserito = airbusService.inserisciNuovo(airbusInput.buildAirbusModel());
		
		System.out.println(AirbusDTO.buildAirbusDTOFromModel(airbusInserito, false));
		
		return AirbusDTO.buildAirbusDTOFromModel(airbusInserito, false);
	}
	
	
	@PutMapping("/{id}")
	public AirbusDTO update(@Valid @RequestBody AirbusDTO airbusInput, @PathVariable(required = true) Long id) {
		Airbus airbus = airbusService.caricaSingoloElemento(id);

		if (airbus == null)
			throw new AirbusNotFoundException("Airbus not found con id: " + id);

		airbusInput.setId(id);
		Airbus airbusAggiornato = airbusService.aggiorna(airbusInput.buildAirbusModel());
		return AirbusDTO.buildAirbusDTOFromModel(airbusAggiornato, false);
	}
	
	/*
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Regista regista = registaService.caricaSingoloElemento(id);

		if (regista == null)
			throw new RegistaNotFoundException("Regista not found con id: " + id);

		registaService.rimuovi(regista);
	}

	@PostMapping("/search")
	public List<RegistaDTO> search(@RequestBody RegistaDTO example) {
		return RegistaDTO.createRegistaDTOListFromModelList(registaService.findByExample(example.buildRegistaModel(), null, null, null).toList(),
				false);
	}
	*/
}