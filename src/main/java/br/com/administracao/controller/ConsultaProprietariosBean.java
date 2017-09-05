package br.com.administracao.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.administracao.model.Proprietario;
import br.com.administracao.repository.Proprietarios;
import br.com.administracao.service.NegocioException;
import br.com.administracao.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class ConsultaProprietariosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Proprietario> proprietarios;

	@Inject
	private Proprietarios proprietarioRepository;
			
	private Proprietario proprietarioSelecionado;
	
	@Transactional
	public void excluir() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.proprietarioRepository.excluir(this.proprietarioSelecionado);
		this.consultar();
		
		context.addMessage(null, new FacesMessage("Proprietário excluído com sucesso!"));
	}
	
	public void consultar() {
		this.proprietarios = proprietarioRepository.todos();
	}

	public List<Proprietario> getProprietarios() {
		return proprietarios;
	}
	
	public Proprietario getProprietarioSelecionado() {
		return proprietarioSelecionado;
	}

	public void setProprietarioSelecionado(Proprietario proprietarioSelecionado) {
		this.proprietarioSelecionado = proprietarioSelecionado;
	}
}
