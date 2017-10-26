/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.service;

import br.com.imunita.vacinasweb.controller.repository.EmpresaRepository;
import br.com.imunita.vacinasweb.controller.repository.VacinaEmpresaRepository;
import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
@RequestScoped
public class VacinaEmpresaService {

    @Inject
    private VacinaEmpresaRepository vacinaEmpresaRepository;

    @Inject
    private EmpresaRepository empresaRepository;

    public List<Empresa> getListaTodosFornecedoresVacina() {
        return empresaRepository.getListaTodasEmpresasFornecedoras();
    }

    public List<Empresa> getListaTodasVacinaFrabricantes() {
        return empresaRepository.getListaTodasEmpresasFabricantes();
    }

    public Empresa getVacinaEmpresaById(Integer idEmpresa) throws Exception {
        return vacinaEmpresaRepository.getVacinaEmpresaById(idEmpresa);
    }

}
