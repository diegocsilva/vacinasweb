/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.ContatoRepository;
import br.com.imunita.vacinasweb.model.entity.Contato;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class ContatoService {

    @Inject
    private ContatoRepository repository;

    public List<Contato> getListaTodosContatos() {
        return this.repository.getListaTodosContatos();
    }
}
