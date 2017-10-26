/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.FuncionarioRepository;
import br.com.imunita.vacinasweb.model.entity.Funcionario;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class FuncionarioService {

    @Inject
    private FuncionarioRepository repository;

    public List<Funcionario> getListaTodosFuncionarios() {
        return repository.getListaTodosFuncionarios();
    }

    public void deletarFuncionario(Funcionario funcionario) throws Exception {
        repository.deletarFuncionario(funcionario);
    }

    public Funcionario salvarFuncionario(Funcionario funcionario) throws Exception {
        return repository.salvarFuncionario(funcionario);
    }

    public Funcionario getFuncionarioById(Integer idFuncionario) throws Exception {
        return repository.getFuncionarioById(idFuncionario);
    }

    public List<Funcionario> getListaFuncionarios(Funcionario funcionario) throws Exception {
        return repository.getListaFuncionarios(funcionario);
    }
}
