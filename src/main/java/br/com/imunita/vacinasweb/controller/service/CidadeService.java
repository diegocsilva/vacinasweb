/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.CidadeRepository;
import br.com.imunita.vacinasweb.model.entity.Cidade;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
@RequestScoped
public class CidadeService {

    @Inject
    private CidadeRepository repository;

    public List<Cidade> getListByFilters(Cidade cidade) {
        return repository.getListByFilters(cidade);
    }

    public List<Cidade> getListaTodasCidades() {
        return repository.getListaTodasCidades();
    }

    public Cidade getCidadeById(Integer idCidade) throws Exception {
        return repository.getCidadeById(idCidade);
    }

    public Cidade salvarCidade(Cidade cidade) throws Exception {
        return repository.salvarCidade(cidade);
    }

    public void deletarCidade(Cidade cidade) throws Exception {
        repository.deletarCidade(cidade);
    }

}
