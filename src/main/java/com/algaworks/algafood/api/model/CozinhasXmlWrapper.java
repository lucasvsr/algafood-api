package com.algaworks.algafood.api.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cozinhas") //Define o nome da raiz do XML
@Data
public class CozinhasXmlWrapper {
	
	@JsonProperty("cozinha") //Define o nome dos elementos da lista que a raiz do XML guarda
	@JacksonXmlElementWrapper(useWrapping = false) //Permite escolher se queremos embrulhar o conteúdo, na maioria dos casos é False
	@NonNull
	private List<Cozinha> cozinhas;

}
