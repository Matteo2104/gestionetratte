package it.prova.gestionetratte.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import it.prova.gestionetratte.model.Airbus;



public class AirbusDTO {
	
	private Long id;
	private String codice;
	private String descrizione;
	private Date dataInizioServizio;
	private int numeroPasseggeri;
	
	private Set<TrattaDTO> tratte = new HashSet<>();
	
	public AirbusDTO() {}
	public AirbusDTO(Long id, String codice, String descrizione, Date dataInizioServizio, int numeroPasseggeri) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
	}
	public Long getId() {
		return id;
	}
	public String getCodice() {
		return codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public Date getDataInizioServizio() {
		return dataInizioServizio;
	}
	public int getNumeroPasseggeri() {
		return numeroPasseggeri;
	}
	public Set<TrattaDTO> getTratte() {
		return tratte;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setDataInizioServizio(Date dataInizioServizio) {
		this.dataInizioServizio = dataInizioServizio;
	}
	public void setNumeroPasseggeri(int numeroPasseggeri) {
		this.numeroPasseggeri = numeroPasseggeri;
	}
	public void setTratte(Set<TrattaDTO> tratte) {
		this.tratte = tratte;
	}
	
	public Airbus buildAirbusModel() {
		return new Airbus(this.id, this.codice, this.descrizione, this.dataInizioServizio, this.numeroPasseggeri);
	}

	public static AirbusDTO buildAirbusDTOFromModel(Airbus airbusModel, boolean includeTratte) {
		AirbusDTO result = new AirbusDTO(airbusModel.getId(), airbusModel.getCodice(), airbusModel.getDescrizione(),
				airbusModel.getDataInizioServizio(), airbusModel.getNumeroPasseggeri());
		
		if(includeTratte)
			result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(airbusModel.getTratte(), false));
		
		return result;
	}

	public static List<AirbusDTO> createAirbusDTOListFromModelList(List<Airbus> modelListInput, boolean includeTratte) {
		return modelListInput.stream().map(airbusEntity -> {
			AirbusDTO result = AirbusDTO.buildAirbusDTOFromModel(airbusEntity, includeTratte);
			if(includeTratte)
				result.setTratte(TrattaDTO.createTrattaDTOSetFromModelSet(airbusEntity.getTratte(), false));
			return result;
		}).collect(Collectors.toList());
	}
	
	
	@Override
	public String toString() {
		return "AirbusDTO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", dataInizioServizio="
				+ dataInizioServizio + ", numeroPasseggeri=" + numeroPasseggeri + "]";
	}
	
	
}
