package it.prova.gestionetratte.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import it.prova.gestionetratte.model.StatoTratta;
import it.prova.gestionetratte.model.Tratta;


public class TrattaDTO {
	
	private Long id;
	private String codice;
	private String descrizione;
	private Date data;
	private LocalTime oraDecollo;
	private LocalTime oraAtterraggio;
	private boolean conSovrapposizioni;
	
	private StatoTratta stato;
	
	private AirbusDTO airbus;
	
	public TrattaDTO() {}
	public TrattaDTO(Long id, String codice, String descrizione, Date data, LocalTime oraDecollo, LocalTime oraAtterraggio, StatoTratta stato) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.oraDecollo = oraDecollo;
		this.oraAtterraggio = oraAtterraggio;
		this.stato = stato;
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
	public Date getData() {
		return data;
	}
	public LocalTime getOraDecollo() {
		return oraDecollo;
	}
	public LocalTime getOraAtterraggio() {
		return oraAtterraggio;
	}
	public StatoTratta getStato() {
		return stato;
	}
	public AirbusDTO getAirbus() {
		return airbus;
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
	public void setData(Date data) {
		this.data = data;
	}
	public void setOraDecollo(LocalTime oraDecollo) {
		this.oraDecollo = oraDecollo;
	}
	public void setOraAtterraggio(LocalTime oraAtterraggio) {
		this.oraAtterraggio = oraAtterraggio;
	}
	public void setStato(StatoTratta stato) {
		this.stato = stato;
	}
	public void setAirbus(AirbusDTO airbus) {
		this.airbus = airbus;
	}
	public boolean isConSovrapposizioni() {
		return conSovrapposizioni;
	}
	public void setConSovrapposizioni(boolean conSovrapposizioni) {
		this.conSovrapposizioni = conSovrapposizioni;
	}
	
	
	
	public Tratta buildTrattaModel() {
		Tratta result = new Tratta(this.id, this.codice, this.descrizione, this.data, this.oraDecollo, this.oraAtterraggio, this.stato);
		
		if (this.airbus != null)
			result.setAirbus(this.airbus.buildAirbusModel());

		return result;
	}

	public static TrattaDTO buildTrattaDTOFromModel(Tratta trattaModel, boolean includeAirbus) {
		TrattaDTO result = new TrattaDTO(trattaModel.getId(), trattaModel.getCodice(), trattaModel.getDescrizione(),
				trattaModel.getData(), trattaModel.getOraDecollo(), trattaModel.getOraAtterraggio(), trattaModel.getStato());

		if (includeAirbus)
			result.setAirbus(AirbusDTO.buildAirbusDTOFromModel(trattaModel.getAirbus(), false));

		return result;
	}
	
	public static Set<TrattaDTO> createTrattaDTOSetFromModelSet(Set<Tratta> modelListInput, boolean includeAirbus) {
		return modelListInput.stream().map(trattaEntity -> {
			return TrattaDTO.buildTrattaDTOFromModel(trattaEntity, includeAirbus);
		}).collect(Collectors.toSet());
	}

	public static List<TrattaDTO> createTrattaDTOListFromModelList(List<Tratta> modelListInput, boolean includeAirbus) {
		return modelListInput.stream().map(trattaEntity -> {
			return TrattaDTO.buildTrattaDTOFromModel(trattaEntity, includeAirbus);
		}).collect(Collectors.toList());
	}

	
	
	
	
	@Override
	public String toString() {
		return "TrattaDTO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", data=" + data
				+ ", oraDecollo=" + oraDecollo + ", oraAtterraggio=" + oraAtterraggio + ", stato=" + stato + ", airbus="
				+ airbus + "]";
	}
	
	
	
	
}
