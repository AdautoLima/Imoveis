package br.com.administracao.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.administracao.model.Proprietario;


public class Proprietarios implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Proprietarios(EntityManager manager) {
		this.manager = manager;
	}
		
	public Proprietario guardar(Proprietario proprietario) {
		return this.manager.merge(proprietario);
	}
		
	public void excluir(Proprietario proprietario) {
		this.manager.remove(manager.getReference(Proprietario.class, proprietario.getId()));
	}
	
	public Proprietario porId(Integer id) {
		return this.manager.find(Proprietario.class, id);
	}
	
	public List<Proprietario> todos() {
		TypedQuery<Proprietario> query = manager.createQuery("from Proprietario", Proprietario.class);
		return query.getResultList();
	}

	public List<String> nomesQueContem(String nome) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct nome from Proprietario where upper(nome) like upper(:nome)",String.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}
	
	
}
