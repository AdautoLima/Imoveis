package br.com.administracao.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.administracao.model.Endereco;
import br.com.administracao.repository.Enderecos;

public class CadastroEnderecos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Enderecos enderecos;

	@Transactional
	public void salvar(Endereco endereco) throws NegocioException {
		this.enderecos.guardar(endereco);
	}
	
	@Transactional
	public void excluir(Endereco endereco) throws NegocioException {
		endereco = this.enderecos.porId(endereco.getId());
		//this.enderecos.remover(endereco);
	}
}
