/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.Vacinacao;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class VacinacaoRepository extends GenericRepository implements Serializable {

    public List<Vacinacao> getListaTodosVacinacaos() {
        return findAll(Vacinacao.class);
    }

    public Vacinacao salvarVacinacao(Vacinacao vacinacao) throws Exception {

        if (vacinacao.getIdVacinacao() != null) {
            return this.atualizarVacinacao(vacinacao);
        } else {
            return this.adicionarVacinacao(vacinacao);
        }
    }

    public Vacinacao getVacinacaoById(Integer idVacinacao) throws Exception {
        return getEntity(Vacinacao.class, idVacinacao);
    }

    public void deletarVacinacao(Vacinacao vacinacao) throws Exception {
        removeEntity(vacinacao);
    }

    private Vacinacao adicionarVacinacao(Vacinacao vacinacao) throws Exception {
        return addEntity(vacinacao);
    }

    private Vacinacao atualizarVacinacao(Vacinacao vacinacao) throws Exception {
        return setEntity(vacinacao);
    }

}
