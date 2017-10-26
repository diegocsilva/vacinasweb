/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.BairroRepository;
import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Bairro;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
@RequestScoped
public class BairroService {

    @Inject
    private BairroRepository repository;

    public List<Bairro> getListaBairroFake() {
        return repository.getListaBairroFake(GeradorRandom.gerarNumero(100));
    }

    public List<Bairro> getListByFilters(Bairro bairro) {
        return repository.getListByFilters(bairro);
    }

    public List<Bairro> getListaTodosBairros() {
        return repository.getListaTodosBairros();
    }

    public Bairro getBairroById(Integer idBairro) throws Exception {
        return repository.getBairroById(idBairro);
    }

    public Bairro salvarBairro(Bairro bairro) throws Exception {
        return repository.salvarBairro(bairro);
    }

    public void deletarBairro(Bairro bairro) throws Exception {
        repository.deletarBairro(bairro);
    }

    public List<Bairro> filtrarBairros(Bairro bairro) {
        return repository.filtrarBairros(bairro);
    }
}
