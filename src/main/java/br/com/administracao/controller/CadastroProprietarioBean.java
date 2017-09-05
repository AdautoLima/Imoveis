package br.com.administracao.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.administracao.model.Endereco;
import br.com.administracao.model.Proprietario;
import br.com.administracao.repository.Enderecos;
import br.com.administracao.repository.Proprietarios;
import br.com.administracao.service.NegocioException;
import br.com.administracao.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class CadastroProprietarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Proprietario proprietario;

	@Inject
	private Proprietarios proprietarios;

	@Inject
	private Enderecos enderecos;
		
	private List<Endereco> todosEnderecos;
		
	public void prepararCadastro() {
						
		this.todosEnderecos = enderecos.todos();	
		
		if (this.proprietario == null) {
			this.proprietario = new Proprietario();			
		}	
		
		if (proprietario.getId() == null){
			proprietario.setDataCadastro(Calendar.getInstance());
		}else{
			proprietario.setDataAlteracao(Calendar.getInstance());
		}	
	}
		
	public List<String> pesquisarNomes(String nome) {
		return this.proprietarios.nomesQueContem(nome);
	}
	
	
	@Transactional
	public void salvar() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (proprietario.getId() == null){
			proprietario.setDataCadastro(Calendar.getInstance());
		}else{
			proprietario.setDataAlteracao(Calendar.getInstance());
		}
		
		this.proprietarios.guardar(proprietario);
			
		this.proprietario = new Proprietario();
		context.addMessage(null, new FacesMessage("Proprietario salvo com sucesso!"));
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public List<Endereco> getTodosEnderecos() {
		return todosEnderecos;
	}

	public void setTodosEnderecos(List<Endereco> todosEnderecos) {
		this.todosEnderecos = todosEnderecos;
	}

	public Enderecos getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Enderecos enderecos) {
		this.enderecos = enderecos;
	}
		
}
