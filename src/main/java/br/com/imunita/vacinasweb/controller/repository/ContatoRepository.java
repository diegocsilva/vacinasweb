/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.Contato;
import java.util.List;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class ContatoRepository extends GenericRepository {

    public List<Contato> getListaTodosContatos() {
        return findAll(Contato.class);
    }

}
