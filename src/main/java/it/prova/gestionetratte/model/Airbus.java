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
	
	public Airbus() {}
	public Airbus(Long id, String codice, String descrizione, Date dataInizioServizio, int numeroPasseggeri) {
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
	public Set<Tratta> getTratte() {
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
	public void setTratte(Set<Tratta> tratte) {
		this.tratte = tratte;
	}
	
	
	
	@Override
	public String toString() {
		return "Airbus [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", dataInizioServizio="
				+ dataInizioServizio + ", numeroPasseggeri=" + numeroPasseggeri + "]";
	}
	
	
	
}
