package br.com.administracao.repository;

import java.io.Serializable;
 
import javax.persistence.Query;
 
import br.com.administracao.model.Usuario;
import br.com.administracao.util.Uteis;
 
 
public class Usuarios implements Serializable {
 
 
	private static final long serialVersionUID = 1L;
 
	public Usuario ValidaUsuario(Usuario usuarioModel){
 
		try {
 
			//QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser) 	
			Query query = Uteis.JpaEntityManager().createNamedQuery("Usuario.findUser");
 
			//PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
 
			//RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (Usuario) query.getSingleResult();
 
		} catch (Exception e) {
 
			return null;
		}
 
 
 
	}
}	