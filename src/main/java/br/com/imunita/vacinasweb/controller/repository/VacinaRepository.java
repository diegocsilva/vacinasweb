/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.Vacina;
import br.com.imunita.vacinasweb.model.entity.Vacinacao;
import br.com.imunita.vacinasweb.model.wrapper.vacinaWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class VacinaRepository extends GenericRepository implements Serializable {

    public List<Vacina> getListaTodasVacinas() {
        return findAll(Vacina.class);
    }

    public Vacina salvarVacina(Vacina vacina) throws Exception {

        if (vacina.getIdVacina() != null) {
            return this.atualizarVacina(vacina);
        } else {
            return this.adicionarVacina(vacina);
        }
    }

    public Vacina getVacinaById(Integer idVacina) throws Exception {
        return getEntity(Vacina.class, idVacina);
    }

    public void deletarVacina(Vacina vacina) throws Exception {
        removeEntity(vacina);
    }

    private Vacina adicionarVacina(Vacina vacina) throws Exception {
        return addEntity(vacina);
    }

    private Vacina atualizarVacina(Vacina vacina) throws Exception {
        return setEntity(vacina);
    }

    public Vacina getVacinaByVacinacao(Vacinacao vacinacao) {
        getEntityManager().createQuery("SELECT vaci FROM Vacinacao AS van INNER JOIN van.loteVacina AS lote INNER JOIN lote.vacina AS vaci WHERE van.idVacinacao IS NOT NULL AND van = :vacinacao ", Vacina.class);
        //TODO: criar hql que consulta a vacina que foi aplicada em uma vacinacao
        return null;
    }

    public List<Vacina> getListaVacinas(vacinaWrapper wrapper) {
        List<Vacina> listaVacinas = new ArrayList<>();

        StringBuilder sql = new StringBuilder(" SELECT v FROM Vacina AS v ");
        sql.append(" LEFT JOIN v.listaFornecedor AS forn ");
        sql.append(" LEFT JOIN v.listaFabricante AS fabr ");

        if (wrapper instanceof vacinaWrapper) {

            sql.append(" WHERE v.idVacina IS NOT NULL ");

            if (wrapper.getNomeVacina() != null && !wrapper.getNomeVacina().isEmpty()) {
                sql.append(" AND v.nome LIKE '%:nomeVacina %' ");
            }
            if (wrapper.getFabricante() != null && wrapper.getFabricante().getIdEmpresa() != null) {
                sql.append(" AND fabr = :fabricante ");
            }
            if (wrapper.getFornecedor() != null && wrapper.getFornecedor().getIdEmpresa() != null) {
                sql.append(" AND forn = :fornecedor ");
            }

            if (wrapper.isUsoAdultoSim()) {
                sql.append(" AND v.usoAdulto = :usoAdultoSim ");
            }

            if (wrapper.isUsoAdultoNao()) {
                sql.append(" AND v.usoAdulto = :usoAdultoNao ");
            }

            if (wrapper.isUsoInfantilSim()) {
                sql.append(" AND v.usoInfantil = :usoInfantilSim ");
            }

            if (wrapper.isUsoInfantilNao()) {
                sql.append(" AND v.usoInfantil = :usoInfantilNao");
            }
        }

        Query query = this.getEntityManager().createQuery(sql.toString(), Vacina.class);

        if (wrapper instanceof vacinaWrapper) {
            if (wrapper.getNomeVacina() != null && !wrapper.getNomeVacina().isEmpty()) {
                query.setParameter("nomeVacina", wrapper.getNomeVacina());
            }
            if (wrapper.getFabricante() != null && wrapper.getFabricante().getIdEmpresa() != null) {
                query.setParameter("fabricante", wrapper.getFabricante());
            }
            if (wrapper.getFornecedor() != null && wrapper.getFornecedor().getIdEmpresa() != null) {
                query.setParameter("fornecedor", wrapper.getFornecedor());
            }

            if (wrapper.isUsoAdultoSim()) {
                query.setParameter("usoAdultoSim", wrapper.isUsoAdultoSim());
            }

            if (wrapper.isUsoAdultoNao()) {
                query.setParameter("usoAdultoNao", wrapper.isUsoAdultoNao());
            }

            if (wrapper.isUsoInfantilSim()) {
                query.setParameter("usoInfantilSim", wrapper.isUsoInfantilSim());
            }

            if (wrapper.isUsoInfantilNao()) {
                query.setParameter("usoInfantilNao", wrapper.isUsoInfantilNao());
            }
        }

        try {
            listaVacinas = (List<Vacina>) query.getResultList();
        } catch (NoResultException e) {
            //nenhum usuario encontrado com o email informado
        }

        return listaVacinas;
    }
}
