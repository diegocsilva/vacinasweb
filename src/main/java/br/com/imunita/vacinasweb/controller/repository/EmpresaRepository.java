/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.Empresa;
import br.com.imunita.vacinasweb.model.entity.Endereco;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

/**
 *
 * @author Diego
 */
@RequestScoped
public class EmpresaRepository extends GenericRepository {

    public List<Empresa> getListaTodasEmpresas() {
        return findAll(Empresa.class);
    }

    public Empresa salvarEmpresa(Empresa empresa) throws Exception {

        //TODO verificar por que persiste objeto instanciado
        if (empresa.getEndereco() instanceof Endereco) {
            if (empresa.getEndereco().getNome() == null || empresa.getEndereco().getNome().isEmpty()) {
                empresa.setEndereco(null);
            }
        }

        if (empresa.getIdEmpresa() != null) {
            return this.atualizarEmpresa(empresa);
        } else {
            return this.adicionarEmpresa(empresa);
        }
    }

    private Empresa adicionarEmpresa(Empresa empresa) throws Exception {
        return addEntity(empresa);
    }

    private Empresa atualizarEmpresa(Empresa empresa) throws Exception {
        return setEntity(empresa);
    }

    public Empresa getEmpresaById(Integer idEmpresa) throws Exception {
        return getEntity(Empresa.class, idEmpresa);
    }

    public void deletarEmpresa(Empresa empresa) throws Exception {
        removeEntity(empresa);
    }

    public List<Empresa> getListaTodasEmpresasFabricantes() {
        Query query = getEntityManager().createQuery("SELECT emp FROM Empresa AS emp WHERE emp.fabricante = :fabricante");
        query.setParameter("fabricante", Boolean.TRUE);
        return query.getResultList();
    }

    public List<Empresa> getListaTodasEmpresasFornecedoras() {
        Query query = getEntityManager().createQuery("SELECT emp FROM Empresa AS emp WHERE emp.fornecedor = :fornecedor");
        query.setParameter("fornecedor", Boolean.TRUE);
        return query.getResultList();
    }

    public List<Empresa> filterByEmpresasFornecedoras(List<Empresa> empresas) {
        List<Empresa> fornecedoresList = new ArrayList<>();
        for (Empresa empresa : empresas) {
            if (empresa.getFornecedor()) {
                fornecedoresList.add(empresa);
            }
        }
        return fornecedoresList;
    }

    public List<Empresa> getListaTodasEmpresasFabricantesFornecedoras() {
        Query query = getEntityManager().createQuery("SELECT emp FROM Empresa AS emp WHERE emp.fabricante = :fabricante OR emp.fornecedor = :fornecedor");
        query.setParameter("fabricante", Boolean.TRUE);
        query.setParameter("fornecedor", Boolean.TRUE);
        return query.getResultList();
    }

    public List<Empresa> getListaEmpresasFabricantes(Vacina vacina) throws Exception {

        Query query = getEntityManager().createQuery("SELECT emp FROM Empresa AS emp WHERE emp.fabricante = :fabricante AND emp.listaVacinasFabricante = :vacina");
        query.setParameter("fabricante", Boolean.TRUE);
        query.setParameter("vacina", vacina);
        return query.getResultList();
    }

    public List<Empresa> getListaEmpresasFornecedoras(Vacina vacina) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
