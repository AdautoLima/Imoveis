package br.com.administracao.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.administracao.model.Estado;

public class Estados implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public Estados(EntityManager manager) {
		this.manager = manager;
	}

	public Estado porSigla(String sigla) {
		return manager.find(Estado.class, sigla);
	}
	
	public List<Estado> todos() {
		TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
		return query.getResultList();
	}	
}
