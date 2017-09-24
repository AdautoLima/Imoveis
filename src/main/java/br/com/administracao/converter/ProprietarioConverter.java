package br.com.administracao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.administracao.model.Proprietario;
import br.com.administracao.util.JPAUtil;

@FacesConverter("proprietarioConverter")
public class ProprietarioConverter implements Converter {

	private EntityManager entity;	
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		Integer id = Integer.valueOf(valor);
		entity = JPAUtil.getEntityManager();
		return entity.find(Proprietario.class, id);
	}
		
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		
		if (object != null) {
			return  ((Proprietario) object).getId().toString();
		}
		return "";
	}
}
