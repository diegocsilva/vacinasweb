/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import br.com.imunita.vacinasweb.controller.security.Seguranca;
import br.com.imunita.vacinasweb.controller.utils.GenericMessages;
import br.com.imunita.vacinasweb.controller.utils.jsf.FacesProducer;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
public abstract class GenericController implements Serializable {

    @Inject
    protected Seguranca seguranca;

    @Inject
    protected GenericMessages messages;

    @Inject
    protected FacesProducer facesProducer;

    public abstract String getNomeTelaManter();

    public abstract String getNomeTelaConsultar();

}
