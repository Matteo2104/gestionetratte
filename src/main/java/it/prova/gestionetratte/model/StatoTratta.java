package it.prova.gestionetratte.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatoTratta {
	ATTIVA, CONCLUSA, ANNULLATA;
	
	@JsonCreator
	public static StatoTratta getStatoTrattaFromCode(String input) {
		if(StringUtils.isBlank(input))
			return null;
		
		for (StatoTratta statoTrattaItem : StatoTratta.values()) {
			if (statoTrattaItem.equals(StatoTratta.valueOf(input))) {
				return statoTrattaItem;
			}
		}
		return null;
	}
}
