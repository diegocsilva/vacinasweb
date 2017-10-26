/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.EstadoRepository;
import br.com.imunita.vacinasweb.model.entity.Estado;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
@RequestScoped
public class EstadoService {

    @Inject
    private EstadoRepository repository;

    public List<Estado> getListByFilters(Estado estado) {
        return repository.getListByFilters(estado);
    }

    public List<Estado> getListaTodosEstados() {
        return repository.getListaTodosEstados();
    }

    public Estado getEstadoById(Integer idEstado) throws Exception {
        return repository.getEstadoById(idEstado);
    }

    public Estado buscaEstado(String nome, String sigla) throws Exception {
        Estado estado = new Estado();
        estado.setNome(nome);
        estado.setSigla(sigla);
        return repository.buscaEstado(estado);
    }

    public Estado salvarEstado(Estado estado) throws Exception {
        return repository.salvarEstado(estado);
    }

    public void deletarEstado(Estado estado) throws Exception {
        repository.deletarEstado(estado);
    }

}
