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

}
