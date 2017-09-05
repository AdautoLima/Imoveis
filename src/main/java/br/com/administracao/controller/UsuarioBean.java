package br.com.administracao.controller;

import java.io.Serializable;
 
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
 
import org.apache.commons.lang3.StringUtils;
 
import br.com.administracao.model.Usuario;
import br.com.administracao.repository.Usuarios;
import br.com.administracao.util.Uteis;
 
@Named(value="usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject
	private Usuario usuario;
 
	@Inject
	private Usuarios usuarios;
 
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
 
	public Usuario GetUsuarioSession(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (Usuario) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
 
	public String Logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}
	public String EfetuarLogin(){
 
		if(StringUtils.isEmpty(usuario.getUsuario()) || StringUtils.isBlank(usuario.getUsuario())){
			Uteis.Mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(usuario.getSenha()) || StringUtils.isBlank(usuario.getSenha())){
 			Uteis.Mensagem("Favor informar a senha!");
			return null;
		}
		else{	
			usuario = usuarios.ValidaUsuario(usuario);
			if(usuario != null){
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuario);
				return "principal/principal?faces-redirect=true";
			}
			else{
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
	}
}
