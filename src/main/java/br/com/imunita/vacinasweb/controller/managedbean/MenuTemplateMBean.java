/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.security.Seguranca;
import br.com.imunita.vacinasweb.model.entity.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@ManagedBean
@ViewScoped
public class MenuTemplateMBean implements Serializable {

    private Usuario usuario;

    @Inject
    private Seguranca seguranca;

    /**
     * Creates a new instance of MenuTemplateMBean
     */
    public MenuTemplateMBean() {
    }

    public Boolean renderizaMenu(String nomeMenu) {

        switch (nomeMenu) {
            /**
             * MENU PRINCIPAL
             */
            case "MENU-PRINCIPAL":
                return true;

            case "MENU-PRINCIPAL-AREAPRIVADA":
                return true;

            case "MENU-PRINCIPAL-SOBRESISTEMA":
                return true;

            /**
             * MENU DA RECEPCAO DO SISTEMA
             */
            case "MENU-RECEPCAO-AGENDA":
                return false;

            case "MENU-RECEPCAO-ATENDIMENTO":
                return false;

            case "MENU-RECEPCAO-PESSOAS":
                return false;

            /**
             * MENU DE CONTATOS
             */
            case "MENU-CONTATO":
                return false;

            case "MENU-SALAVACINAS-ESTOQUEVACINA":
                return true;

            /**
             * MENU DE RELATORIOS
             */
            case "MENU-RELATORIOS":
                return false;
        }

        return seguranca.hasPermission(nomeMenu);
    }

}
