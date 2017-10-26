/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.EntradaEstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.EstoqueVacina;
import br.com.imunita.vacinasweb.model.entity.LoteVacina;
import br.com.imunita.vacinasweb.model.entity.SaidaEstoqueVacina;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

/**
 *
 * @author rodolpho.sotolani
 */
@RequestScoped
public class EstoqueVacinaRepository extends GenericRepository {

    public List<EstoqueVacina> getListaTodosEstoqueVacinas() {
        return findAll(EstoqueVacina.class);
    }

    public EstoqueVacina getEstoqueVacina(Integer idEstoqueVacinas) throws Exception {
        if (idEstoqueVacinas != null && idEstoqueVacinas > 0) {
            return getEntity(EstoqueVacina.class, idEstoqueVacinas);
        }
        return null;
    }

    public EstoqueVacina salvar(EstoqueVacina estoqueVacina) throws Exception {
        if (estoqueVacina.getIdEstoqueVacina() != null) {
            return this.atualizar(estoqueVacina);
        } else {
            return this.adicionar(estoqueVacina);
        }
    }

    public void deletar(EstoqueVacina estoqueVacina) throws Exception {
        removeEntity(estoqueVacina);
    }

    public EntradaEstoqueVacina salvarEntradaEstoque(EntradaEstoqueVacina entradaEstoque) throws Exception {
        if (entradaEstoque.getIdEntradaEstoque() != null) {
            return this.atualizarEntradaEstoque(entradaEstoque);
        } else {
            return this.adicionarEntradaEstoque(entradaEstoque);
        }
    }

    public void deletarEntradaEstoque(EntradaEstoqueVacina entradaEstoque) throws Exception {
        removeEntity(entradaEstoque);
    }

    private EstoqueVacina atualizar(EstoqueVacina estoqueVacina) throws Exception {
        return setEntity(estoqueVacina);
    }

    private EstoqueVacina adicionar(EstoqueVacina estoqueVacina) throws Exception {
        return addEntity(estoqueVacina);
    }

    private EntradaEstoqueVacina atualizarEntradaEstoque(EntradaEstoqueVacina entradaEstoque) throws Exception {
        return setEntity(entradaEstoque);
    }

    private EntradaEstoqueVacina adicionarEntradaEstoque(EntradaEstoqueVacina entradaEstoque) throws Exception {
        return addEntity(entradaEstoque);
    }

    public SaidaEstoqueVacina salvarSaidaEstoque(SaidaEstoqueVacina saidaEstoque) throws Exception {
        if (saidaEstoque.getIdSaidaEstoque() != null) {
            return this.atualizarSaidaEstoque(saidaEstoque);
        } else {
            return this.adicionarSaidaEstoque(saidaEstoque);
        }
    }

    public void deletarSaidaEstoque(SaidaEstoqueVacina saidaEstoque) throws Exception {
        removeEntity(saidaEstoque);
    }

    private SaidaEstoqueVacina atualizarSaidaEstoque(SaidaEstoqueVacina saidaEstoque) throws Exception {
        return setEntity(saidaEstoque);
    }

    private SaidaEstoqueVacina adicionarSaidaEstoque(SaidaEstoqueVacina saidaEstoque) throws Exception {
        return addEntity(saidaEstoque);
    }

    public EstoqueVacina getEstoqueVacina(LoteVacina loteVacina) {
        Query query = getEntityManager().createQuery("SELECT est FROM EstoqueVacina AS est WHERE est.idEstoqueVacina IS NOT NULL AND est.loteVacina = :loteVacina ");
        query.setParameter("loteVacina", loteVacina);
        return (EstoqueVacina) query.getSingleResult();
    }

}
