package br.com.administracao.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.administracao.model.Estado;
import br.com.administracao.repository.Estados;
import br.com.administracao.util.JPAUtil;

@Named
@javax.faces.view.ViewScoped
public class ConsultaEstados implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Estado> estados;

	public void consultar() {
		EntityManager manager = JPAUtil.getEntityManager();
		Estados estados = new Estados(manager);
		this.estados = estados.todos();
		manager.close();
	}

	public List<Estado> getEstados() {
		return estados;
	}
}
