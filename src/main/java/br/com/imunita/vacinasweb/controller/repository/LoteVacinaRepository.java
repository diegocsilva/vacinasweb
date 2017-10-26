/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.Vacina;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class LoteVacinaRepository extends GenericRepository {

    public List<LoteVacina> getListaTodosLotesVacina() {
        return findAll(LoteVacina.class);
    }

    public List<LoteVacina> getListaLotesVacina(Vacina vacina) {
        Query query = getEntityManager().createQuery("SELECT lote FROM LoteVacina AS lote WHERE lote.vacina = :vacina AND lote.ativo = :ativo ");
        query.setParameter("vacina", vacina);
        query.setParameter("ativo", Boolean.TRUE);
        return query.getResultList();
    }

    public LoteVacina salvar(LoteVacina loteVacina) throws Exception {
        if (loteVacina.getIdLoteVacina() == null) {
            addEntity(loteVacina);
        } else {
            setEntity(loteVacina);
        }
        return loteVacina;
    }

    public void deletar(LoteVacina loteVacina) throws Exception {
        removeEntity(loteVacina);
    }

}
