package br.com.administracao.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.administracao.model.Imovel;
import br.com.administracao.repository.Imoveis;
import br.com.administracao.service.NegocioException;
import br.com.administracao.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class ConsultaImoveisBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Imovel> imoveis;

	@Inject
	private Imoveis imovelRepository;
			
	private Imovel imovelSelecionado;
	
	@Transactional
	public void excluir() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		this.imovelRepository.excluir(this.imovelSelecionado);
		this.consultar();
		
		context.addMessage(null, new FacesMessage("Imóvel excluído com sucesso!"));
	}
	
	public void consultar() {
		this.imoveis = imovelRepository.todos();
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}
	
	public Imovel getImovelSelecionado() {
		return imovelSelecionado;
	}

	public void setImovelSelecionado(Imovel imovelSelecionado) {
		this.imovelSelecionado = imovelSelecionado;
	}
}
