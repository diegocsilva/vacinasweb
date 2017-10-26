/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.model.entity.TipoFuncionario;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class TipoFuncionarioRepository extends GenericRepository {

    public List<TipoFuncionario> getListaTodosTiposFuncionario() {
        return findAll(TipoFuncionario.class);
    }

    public TipoFuncionario salvarTipoFuncionario(TipoFuncionario tipoFuncionario) throws Exception {

        if (tipoFuncionario.getIdTipoFuncionario() != null) {
            return this.atualizarTipoFuncionario(tipoFuncionario);
        } else {
            return this.adicionarTipoFuncionario(tipoFuncionario);
        }
    }

    private TipoFuncionario adicionarTipoFuncionario(TipoFuncionario tipoFuncionario) throws Exception {
        return addEntity(tipoFuncionario);
    }

    private TipoFuncionario atualizarTipoFuncionario(TipoFuncionario tipoFuncionario) throws Exception {
        return setEntity(tipoFuncionario);
    }

    public TipoFuncionario getTipoFuncionarioById(Integer idTipoFuncionario) throws Exception {
        return getEntity(TipoFuncionario.class, idTipoFuncionario);
    }

    public void deletarTipoFuncionario(TipoFuncionario tipoFuncionario) throws Exception {
        removeEntity(tipoFuncionario);
    }

    public List<TipoFuncionario> getTipoFuncionarios(TipoFuncionario tipoFuncionario) {
        StringBuilder sql = new StringBuilder("SELECT tipo FROM TipoFuncionario AS tipo WHERE tipo.idTipoFuncionario IS NOT NULL ");
        if (!tipoFuncionario.getDescricao().isEmpty()) {
            sql.append("AND tipo.descricao LIKE '%");
            sql.append(tipoFuncionario.getDescricao());
            sql.append("%'");
        }

        if (!tipoFuncionario.getNome().isEmpty()) {
            sql.append("AND tipo.nome LIKE '%");
            sql.append(tipoFuncionario.getNome());
            sql.append("%'");
        }
        Query query = getEntityManager().createQuery(sql.toString());
        return query.getResultList();
    }
}
