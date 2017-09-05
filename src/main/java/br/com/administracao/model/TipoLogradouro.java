package br.com.administracao.model;

public enum TipoLogradouro {

	AVENIDA("Avenida"), 
	RUA("Rua"), 
	PRACA("Pra�a"), 
	VIELA("Viela"), 
	RODOVIA("Rodovia"), 
	ALAMEDA("Alameda"),
	ESTRADA("Estrada"),
	ESPLANADA("Esplanada"),
	ESTACAO("Esta��o"),
	LADEIRA("Ladeira");
	
	private String descricao;
	
	TipoLogradouro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
