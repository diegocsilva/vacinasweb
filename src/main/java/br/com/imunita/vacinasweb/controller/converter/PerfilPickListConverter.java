/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.converter;

import br.com.imunita.vacinasweb.model.entity.Perfil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Rodolpho
 */
@FacesConverter(value = "perfilPickListConverter")
public class PerfilPickListConverter implements Converter {

    @Override
    public Perfil getAsObject(FacesContext context, UIComponent component, String value) {

        Perfil perfilRetorno = null;

        if (component instanceof PickList) {
            Object dualList = ((PickList) component).getValue();
            DualListModel dualListModel = (DualListModel) dualList;
            for (Object source : dualListModel.getSource()) {
                String id = "" + ((Perfil) source).getIdPerfil();
                if (value.equals(id)) {
                    perfilRetorno = (Perfil) source;
                    break;
                }
            }
            if (perfilRetorno == null) {
                for (Object target : dualListModel.getTarget()) {
                    String id = "" + ((Perfil) target).getIdPerfil();
                    if (value.equals(id)) {
                        perfilRetorno = (Perfil) target;
                        break;
                    }
                }
            }
        }
        return perfilRetorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String str = "";
        if (value instanceof Perfil) {
            str = "" + ((Perfil) value).getIdPerfil();
        }
        return str;
    }

}
