package br.com.administracao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * <b>Converter para CPF.</b>
 *
 * @author Dilnei Cunha 
 */
@FacesConverter("CPFConverter")
public class CPFConverter implements Converter {

    /**
     * <b>M�todo que remove a mascara do CPF.</b>
     *
     * @param facesContext
     * @param uIcomponent
     * @param cpf
     * @return Object
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uIcomponent, String cpf) {
        if (cpf.trim().equals("")) {
            return null;
        } else {
            cpf = cpf.replace("-", "");
            cpf = cpf.replace(".", "");
            return cpf;
        }
    }

    /**
     * <b>M�todo que retorna a String do CPF.</b>
     *
     * @param facesContext
     * @param uIcomponent
     * @param object
     * @return String
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uIcomponent, Object object) {
        return object.toString();
    }
}