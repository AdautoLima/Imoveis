package br.com.administracao.model;

public enum TipoLogradouro {

	AVENIDA("Avenida"), 
	RUA("Rua"), 
	PRACA("Praça"), 
	VIELA("Viela"), 
	RODOVIA("Rodovia"), 
	ALAMEDA("Alameda"),
	ESTRADA("Estrada"),
	ESPLANADA("Esplanada"),
	ESTACAO("Estação"),
	LADEIRA("Ladeira");
	
	private String descricao;
	
	TipoLogradouro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
