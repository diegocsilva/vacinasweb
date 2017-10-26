/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.TipoFuncionarioRepository;
import br.com.imunita.vacinasweb.model.entity.TipoFuncionario;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class TipoFuncionarioService {

    @Inject
    private TipoFuncionarioRepository repository;

    public List<TipoFuncionario> getListaTodosTipoFuncionarios() {
        return repository.getListaTodosTiposFuncionario();
    }

    public TipoFuncionario getTipoFuncionarioById(Integer idTipoFuncionario) throws Exception {
        return repository.getTipoFuncionarioById(idTipoFuncionario);
    }

    public TipoFuncionario salvarTipoFuncionario(TipoFuncionario TipoFuncionario) throws Exception {
        return repository.salvarTipoFuncionario(TipoFuncionario);
    }

    public void deletarTipoFuncionario(TipoFuncionario TipoFuncionario) throws Exception {
        repository.deletarTipoFuncionario(TipoFuncionario);
    }

    public List<TipoFuncionario> getTipoFuncionarios(TipoFuncionario tipoFuncionario) {
        return repository.getTipoFuncionarios(tipoFuncionario);
    }
}
