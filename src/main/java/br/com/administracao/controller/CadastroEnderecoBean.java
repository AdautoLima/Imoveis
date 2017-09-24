package br.com.administracao.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.administracao.model.Endereco;
import br.com.administracao.model.Estado;
import br.com.administracao.model.TipoLogradouro;
import br.com.administracao.repository.Enderecos;
import br.com.administracao.repository.Estados;
import br.com.administracao.service.NegocioException;
import br.com.administracao.util.FacesUtil;
import br.com.administracao.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class CadastroEnderecoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Endereco endereco;

	@Inject
	private Estados estados;
	
	@Inject
	private Enderecos enderecos;
	
	private List<Estado> todosEstados;
	
	public void prepararCadastro() {
		this.todosEstados = this.estados.todos();
			
		if (this.endereco == null) {
			this.endereco = new Endereco();			
		}	
		
		if (endereco.getId() == null)
			endereco.setDataCadastro(Calendar.getInstance());
		else
			endereco.setDataAlteracao(Calendar.getInstance());				
	}
	
	public TipoLogradouro[] getTiposLogradouro(){
		return TipoLogradouro.values();
	}
			
	public List<String> pesquisarLogradouros(String logradouro) {
		return this.enderecos.logradourosQueContem(logradouro);
	}
			
	@Transactional
	public void salvar() throws NegocioException {
		if (endereco.getId() == null)
			endereco.setDataCadastro(Calendar.getInstance());
		else
			endereco.setDataAlteracao(Calendar.getInstance());
								
		if (enderecos.comDadosIguais(endereco) != null){
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Este endereço já está cadastrado na base!");			
		}else{
			this.enderecos.guardar(endereco);
			this.endereco = new Endereco();
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Endereço salvo com sucesso!");			
		}
	}

	public List<Estado> getTodosEstados() {
		return this.todosEstados;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
		
}



//public EnumSet<TipoLogradouro> listarLogradouros() {
	//    return EnumSet.allOf(TipoLogradouro.class);
	//}