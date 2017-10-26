/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.converter;

import br.com.imunita.vacinasweb.model.entity.Permissao;
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
@FacesConverter(value = "permissaoPickListConverter")
public class PermissaoPickListConverter implements Converter {

    @Override
    public Permissao getAsObject(FacesContext context, UIComponent component, String value) {

        Permissao permissaoRetorno = null;

        if (component instanceof PickList) {
            Object dualList = ((PickList) component).getValue();
            DualListModel dualListModel = (DualListModel) dualList;
            for (Object source : dualListModel.getSource()) {
                String id = "" + ((Permissao) source).getIdPermissao();
                if (value.equals(id)) {
                    permissaoRetorno = (Permissao) source;
                    break;
                }
            }
            if (permissaoRetorno == null) {
                for (Object target : dualListModel.getTarget()) {
                    String id = "" + ((Permissao) target).getIdPermissao();
                    if (value.equals(id)) {
                        permissaoRetorno = (Permissao) target;
                        break;
                    }
                }
            }
        }
        return permissaoRetorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String str = "";
        if (value instanceof Permissao) {
            str = "" + ((Permissao) value).getIdPermissao();
        }
        return str;
    }

}
