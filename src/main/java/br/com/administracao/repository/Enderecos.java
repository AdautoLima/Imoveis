package br.com.administracao.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.administracao.model.Endereco;


public class Enderecos implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Enderecos(EntityManager manager) {
		this.manager = manager;
	}
		
	public Endereco guardar(Endereco endereco) {
		return this.manager.merge(endereco);
	}
	
	public void adicionar(Endereco endereco) {
		this.manager.persist(endereco);
	}

	public void excluir(Endereco endereco) {
		this.manager.remove(manager.getReference(Endereco.class, endereco.getId()));
	}
	
	public Endereco porId(Integer id) {
		return this.manager.find(Endereco.class, id);
	}
	
	public List<Endereco> todos() {
		TypedQuery<Endereco> query = manager.createQuery("from Endereco", Endereco.class);
		return query.getResultList();
	}

	public List<String> logradourosQueContem(String logradouro) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct logradouro from Endereco " + "where upper(logradouro) like upper(:logradouro)",String.class);
		query.setParameter("logradouro", "%" + logradouro + "%");
		return query.getResultList();
	}
	
	
}
