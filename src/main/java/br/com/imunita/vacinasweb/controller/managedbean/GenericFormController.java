/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author rodolpho.sotolani
 */
@RequestScoped
public abstract class GenericFormController extends GenericController implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract String salvar();

    public abstract String cancelar();

    public abstract String novo();

    public abstract String deletar();

    public abstract Boolean renderizarBotaoSalvar();

    public abstract Boolean renderizarBotaoNovo();

    public abstract Boolean renderizarBotaoDeletar();

}
