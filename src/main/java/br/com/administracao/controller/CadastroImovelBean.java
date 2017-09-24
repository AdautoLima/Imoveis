package br.com.administracao.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.administracao.model.Endereco;
import br.com.administracao.model.Imovel;
import br.com.administracao.model.Proprietario;
import br.com.administracao.repository.Enderecos;
import br.com.administracao.repository.Imoveis;
import br.com.administracao.repository.Proprietarios;
import br.com.administracao.service.NegocioException;
import br.com.administracao.util.FacesUtil;
import br.com.administracao.util.Transactional;

@Named
@javax.faces.view.ViewScoped
public class CadastroImovelBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Imovel imovel;
	
	@Inject
	private UsuarioBean usuarioLogado;

	@Inject
	private Imoveis imoveis;

	@Inject
	private Enderecos enderecos;
		
	@Inject
	private Proprietarios proprietarios;
	
	private List<Endereco> todosEnderecos;
	private List<Proprietario> todosProprietarios;
	
	public void prepararCadastro() {
						
		this.todosEnderecos = enderecos.todos();	
		this.todosProprietarios = proprietarios.todos();
		
		if (this.imovel == null) {
			this.imovel = new Imovel();			
		}
		
		if (imovel.getId() == null){			
			imovel.setDataCadastro(Calendar.getInstance());
			imovel.setUsuarioCadastro(usuarioLogado.getUsuario());
		}else{
			this.imovel = imoveis.porId(imovel.getId());
			imovel.setDataAlteracao(Calendar.getInstance());
			imovel.setUsuarioAlteracao(usuarioLogado.getUsuario());
		}	
	}
		
	//public List<String> pesquisarNomes(String nome) {
	//	return this.proprietarios.nomesQueContem(nome);
	//}
		
	public String cancelar() {
		this.imovel = new Imovel();		
		return "ConsultaImovel?faces-redirect=true";		
	}	
	
	@Transactional
	public void salvar() throws NegocioException {
		if (imovel.getId() == null){
			imovel.setDataCadastro(Calendar.getInstance());
			imovel.setUsuarioCadastro(usuarioLogado.getUsuario());                               			
		}else{
			imovel.setDataAlteracao(Calendar.getInstance());
			imovel.setUsuarioAlteracao(usuarioLogado.getUsuario());
		}
		
		this.imoveis.guardar(imovel);
			
		this.imovel = new Imovel();
		FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Imóvel salvo com sucesso!");
	}
	
	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Enderecos getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Enderecos enderecos) {
		this.enderecos = enderecos;
	}

	public List<Endereco> getTodosEnderecos() {
		return todosEnderecos;
	}

	public void setTodosEnderecos(List<Endereco> todosEnderecos) {
		this.todosEnderecos = todosEnderecos;
	}

	public Proprietarios getProprietarios() {
		return proprietarios;
	}

	public void setProprietarios(Proprietarios proprietarios) {
		this.proprietarios = proprietarios;
	}

	public List<Proprietario> getTodosProprietarios() {
		return todosProprietarios;
	}

	public void setTodosProprietarios(List<Proprietario> todosProprietarios) {
		this.todosProprietarios = todosProprietarios;
	}
	
	
	
}


//public EnumSet<TipoLogradouro> listarLogradouros() {
	//    return EnumSet.allOf(TipoLogradouro.class);
	//}