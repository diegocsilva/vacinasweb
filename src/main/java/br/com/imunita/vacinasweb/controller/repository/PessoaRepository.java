/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.Pessoa;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class PessoaRepository extends GenericRepository implements Serializable {

    public Pessoa salvar(Pessoa pessoa) throws Exception {
        if (pessoa.getId() != null) {
            return setEntity(pessoa);
        } else {
            return addEntity(pessoa);
        }
    }

}
