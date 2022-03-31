package it.prova.gestionetratte.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "airbus")
public class Airbus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "codice")
	private String codice;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "dataInizioServizio")
	private Date dataInizioServizio;
	@Column(name = "numeroPasseggeri")
	private int numeroPasseggeri;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "airbus")
	private Set<Tratta> tratte = new HashSet<>();
	
	
}
