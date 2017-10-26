/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.EmpresaRepository;
import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
@RequestScoped
public class EmpresaService {

    @Inject
    private EmpresaRepository repository;

    public List<Empresa> getListaTodasEmpresas() {
        return repository.getListaTodasEmpresas();
    }

    public Empresa getEmpresaById(Integer idEmpresa) throws Exception {
        return repository.getEmpresaById(idEmpresa);
    }

    public Empresa salvarEmpresa(Empresa empresa) throws Exception {
        return repository.salvarEmpresa(empresa);
    }

    public void deletarEmpresa(Empresa empresa) throws Exception {
        repository.deletarEmpresa(empresa);
    }

    public List<Empresa> getListaTodasEmpresasFabricantes() {
        return repository.getListaTodasEmpresasFabricantes();
    }

    public List<Empresa> getListaTodasEmpresasFornecedoras() {
        return repository.getListaTodasEmpresasFornecedoras();
    }

    public List<Empresa> getListaTodasEmpresasFabricantesFornecedoras() {
        return this.repository.getListaTodasEmpresasFabricantesFornecedoras();
    }

    public List<Empresa> getListaEmpresasFabricantes(Vacina vacina) {
        try {
            return this.repository.getListaEmpresasFabricantes(vacina);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public List<Empresa> getListaEmpresasFornecedoras(Vacina vacina) {
        try {
            return this.repository.getListaEmpresasFornecedoras(vacina);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
}
