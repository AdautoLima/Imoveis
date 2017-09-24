package br.com.administracao.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.administracao.model.Endereco;
import br.com.administracao.model.Proprietario;
import br.com.administracao.repository.Enderecos;
import br.com.administracao.repository.Proprietarios;
import br.com.administracao.service.NegocioException;
import br.com.administracao.util.FacesUtil;
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
			this.proprietario = proprietarios.porId(proprietario.getId());
			proprietario.setDataAlteracao(Calendar.getInstance());
		}	
	}
		
	public List<String> pesquisarNomes(String nome) {
		return this.proprietarios.nomesQueContem(nome);
	}
		
	@Transactional
	public void salvar() throws NegocioException {
		if (proprietario.getId() == null){
			proprietario.setDataCadastro(Calendar.getInstance());
		}else{
			proprietario.setDataAlteracao(Calendar.getInstance());
		}
		
		this.proprietarios.guardar(proprietario);
			
		this.proprietario = new Proprietario();
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Proprietário salvo com sucesso!");
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
