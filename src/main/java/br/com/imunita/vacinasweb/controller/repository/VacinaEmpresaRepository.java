/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.hibernate.Criteria;

/**
 *
 * @author Diego
 */
@RequestScoped
public class VacinaEmpresaRepository extends GenericRepository {

    @Inject
    private EmpresaRepository empresaRepository;

    public List<Vacina> getListaTodosVacinaEmpresa(Vacina vacina) {
        Criteria criteria = createCriteria(vacina);

        return criteria.list();
    }

    public Empresa getVacinaEmpresaById(Integer idEmpresa) throws Exception {
        return getEntity(Empresa.class, idEmpresa);
    }

    public EmpresaRepository getEmpresaRepository() {
        return empresaRepository;
    }

    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    private List<Empresa> getListEmpresaByVacinaEmpresa(List<Vacina> vacina) {
        List<Empresa> empresas = new ArrayList<>();

        return empresas;
    }
}
