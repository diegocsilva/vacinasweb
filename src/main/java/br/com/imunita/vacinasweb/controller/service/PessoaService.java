/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.PessoaRepository;
import br.com.imunita.vacinasweb.model.entity.Pessoa;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class PessoaService implements Serializable {

    @Inject
    private PessoaRepository repository;

    public Pessoa salvar(Pessoa pessoa) throws Exception {
        return repository.salvar(pessoa);
    }

}
