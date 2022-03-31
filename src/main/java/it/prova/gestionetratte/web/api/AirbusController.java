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
import it.prova.gestionetratte.service.airbus.AirbusService;


@RestController
@RequestMapping("api/airbus")
public class AirbusController {
	
	@Autowired
	private AirbusService airbusService;
	
	@GetMapping
	public List<AirbusDTO> getAll() {
		return AirbusDTO.createAirbusDTOListFromModelList(airbusService.listAllElementsEager(), true);
	}
	
	/*
	@GetMapping("/{id}")
	public RegistaDTO findById(@PathVariable(value = "id", required = true) long id) {
		Regista regista = registaService.caricaSingoloElementoConFilms(id);

		if (regista == null)
			throw new RegistaNotFoundException("Regista not found con id: " + id);

		return RegistaDTO.buildRegistaDTOFromModel(regista, true);
	}
	*/
	
	
//	// gli errori di validazione vengono mostrati con 400 Bad Request ma
//	// elencandoli grazie al ControllerAdvice
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public RegistaDTO createNew(@Valid @RequestBody RegistaDTO registaInput) {
//		//se mi viene inviato un id jpa lo interpreta come update ed a me (producer) non sta bene
//		if(registaInput.getId() != null)
//			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
//		
//		Regista registaInserito = registaService.inserisciNuovo(registaInput.buildRegistaModel());
//		return RegistaDTO.buildRegistaDTOFromModel(registaInserito, false);
//	}
	
	/*
	@PutMapping("/{id}")
	public RegistaDTO update(@Valid @RequestBody RegistaDTO registaInput, @PathVariable(required = true) Long id) {
		Regista regista = registaService.caricaSingoloElemento(id);

		if (regista == null)
			throw new RegistaNotFoundException("Regista not found con id: " + id);

		registaInput.setId(id);
		Regista registaAggiornato = registaService.aggiorna(registaInput.buildRegistaModel());
		return RegistaDTO.buildRegistaDTOFromModel(registaAggiornato, false);
	}

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
