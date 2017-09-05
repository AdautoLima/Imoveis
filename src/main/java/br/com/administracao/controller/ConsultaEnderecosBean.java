package br.com.administracao.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.administracao.model.Endereco;
import br.com.administracao.repository.Enderecos;
import br.com.administracao.service.NegocioException;
import br.com.administracao.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class ConsultaEnderecosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Endereco> enderecos;

	@Inject
	private Enderecos enderecoRepository;
			
	private Endereco enderecoSelecionado;
	
	@Transactional
	public void excluir() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.enderecoRepository.excluir(this.enderecoSelecionado);
		this.consultar();
		
		context.addMessage(null, new FacesMessage("Endereço excluído com sucesso!"));
	}
	
	public void consultar() {
		this.enderecos = enderecoRepository.todos();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	
	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}
}
