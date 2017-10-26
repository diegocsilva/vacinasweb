/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.managedbean;

import java.io.Serializable;

/**
 *
 * @author rodolpho.sotolani
 */
public abstract class GenericListController extends GenericController implements Serializable {

    public abstract void filtrar();

    public abstract String novo();

    public abstract String editar(Object objeto);

    public abstract String deletar(Object objeto);

    public abstract void visualizar(Object objeto);

    public abstract String deletarVarios();

    public abstract Boolean renderizarBotaoNovo();

    public abstract Boolean renderizarBotaoDeletarVarios();

    public abstract Boolean renderizarBotaoVisualizar();

    public abstract Boolean renderizarBotaoEditar();

    public abstract Boolean renderizarBotaoDeletar();

}
