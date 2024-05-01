package com.aziz.voyages.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity

public class voyage {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idvoyage;
	private String nomvoyage;
	private Double prixvoyage;
	private Date dateCreation;
	@ManyToOne
	private Categorie categorie;
	public Long getIdvoyage() {
		return idvoyage;
	}
	public voyage() {
		super();
	
	}
	public voyage(String nomvoyage, Double prixvoyage, Date dateCreation) {
		super();
		this.nomvoyage = nomvoyage;
		this.prixvoyage = prixvoyage;
		this.dateCreation = dateCreation;
	}
	public void setIdvoyage(Long idvoyage) {
		this.idvoyage = idvoyage;
	}
	public String getNomvoyage() {
		return nomvoyage;
	}
	public void setNomvoyage(String nomvoyage) {
		this.nomvoyage = nomvoyage;
	}
	public Double getPrixvoyage() {
		return prixvoyage;
	}
	public void setPrixvoyage(Double prixvoyage) {
		this.prixvoyage = prixvoyage;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	@Override
	public String toString() {
		return "voyage [idvoyage=" + idvoyage + ", nomvoyage=" + nomvoyage + ", prixvoyage=" + prixvoyage
				+ ", dateCreation=" + dateCreation + "]";
	}

	
	

}
