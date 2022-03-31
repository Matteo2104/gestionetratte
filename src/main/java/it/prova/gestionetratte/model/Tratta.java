package it.prova.gestionetratte.model;

import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tratta")
public class Tratta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "codice")
	private String codice;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "data")
	private Date data;
	@Column(name = "oraDecollo")
	private LocalTime oraDecollo;
	@Column(name = "oraAtterraggio")
	private LocalTime oraAtterraggio;
	
	@Column(name = "stato")
	@Enumerated(EnumType.STRING)
	private StatoTratta stato;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airbus_id", nullable = false)
	private Airbus airbus;
	
	public Tratta() {}
	public Tratta(Long id, String codice, String descrizione, Date data, LocalTime oraDecollo, LocalTime oraAtterraggio, StatoTratta stato) {
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

	public Airbus getAirbus() {
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

	public void setAirbus(Airbus airbus) {
		this.airbus = airbus;
	}
	
	

}
