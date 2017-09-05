package br.com.administracao.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
 
/***
 * ESSE FILTER VAI SER CHAMADO TODA VEZ QUE FOR REALIZADO 
 * UMA REQUISIÇÃO PARA O FACES SERVLET.
 * */
@WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {
 
 
	@Inject
	private EntityManager manager;
 
	//private String persistence_unit_name = "ImoveisPU";
 
    public JPAFilter() {
 
    }
 
	public void destroy() {
 
		this.manager.close();
	}
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
		/*CRIANDO UM ENTITYMANAGER*/
		//EntityManager entityManager =  this.entityManagerFactory.createEntityManager();
 
		/*ADICIONANDO ELE NA REQUISIÇÃO*/
		request.setAttribute("entityManager", manager);
 
		/*INICIANDO UMA TRANSAÇÃO*/
		manager.getTransaction().begin();
 
		/*INICIANDO FACES SERVLET*/
		chain.doFilter(request, response);
 
		try {
 
			/*SE NÃO TIVER ERRO NA OPERAÇÃO ELE EXECUTA O COMMIT*/
			manager.getTransaction().commit();
 
		} catch (Exception e) {
 
			/*SE TIVER ERRO NA OPERAÇÃO É EXECUTADO O rollback*/
			manager.getTransaction().rollback();
		}
		finally{
 
			/*DEPOIS DE DAR O COMMIT OU ROLLBACK ELE FINALIZA O entityManager*/
			manager.close();
		}
	}
 
	//public void init(FilterConfig fConfig) throws ServletException {
 
		/*CRIA O entityManagerFactory COM OS PARÂMETROS DEFINIDOS NO persistence.xml*/
	//	this.manager = Persistence.createEntityManagerFactory(this.persistence_unit_name); 
	//}
}
