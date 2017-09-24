package br.com.administracao.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.administracao.model.Imovel;


public class Imoveis implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Imoveis(EntityManager manager) {
		this.manager = manager;
	}
		
	public Imovel guardar(Imovel imovel) {
		return this.manager.merge(imovel);
	}
	
	public void adicionar(Imovel imovel) {
		this.manager.persist(imovel);
	}

	public void excluir(Imovel imovel) {
		this.manager.remove(manager.getReference(Imovel.class, imovel.getId()));
	}
	
	public Imovel porId(Integer id) {
		return this.manager.find(Imovel.class, id);
	}
	
	public List<Imovel> todos() {
		TypedQuery<Imovel> query = manager.createQuery("from Imovel", Imovel.class);
		return query.getResultList();
	}

	/*
	public List<String> logradourosQueContem(String logradouro) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct logradouro from Endereco " + "where upper(logradouro) like upper(:logradouro)",String.class);
		query.setParameter("logradouro", "%" + logradouro + "%");
		return query.getResultList();
	}
	*/
	
}
