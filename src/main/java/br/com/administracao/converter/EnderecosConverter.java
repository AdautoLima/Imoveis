package br.com.administracao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.administracao.model.Endereco;
import br.com.administracao.repository.Enderecos;


@FacesConverter(forClass = Endereco.class)
public class EnderecosConverter implements Converter {
	
	@Inject
	private Enderecos enderecos;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Endereco retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.enderecos.porId(new Integer(value));			
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Endereco endereco = ((Endereco) value);
			return endereco.getId() == null ? null : endereco.getId().toString();
		}
		return null;
	}
}
