/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Permissao;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import org.hibernate.Criteria;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public class PermissaoRepository extends GenericRepository {

    public List<Permissao> getListaPermissoesFake(Integer qtdeRegistros) {

        Permissao permissao;
        List<Permissao> listaRetorno = new ArrayList<>();

        for (int i = 0; i < qtdeRegistros; i++) {
            permissao = new Permissao();

            permissao.setIdPermissao(i);
            permissao.setNome(GeradorRandom.gerarTexto(2));
            permissao.setDescricao(GeradorRandom.gerarTexto(10));

            listaRetorno.add(permissao);
        }

        return listaRetorno;
    }

    public List<Permissao> getListaTodasPermissoes() {
        return findAll(Permissao.class);
    }

    public List<Permissao> getListByFilters(Permissao permissao) {
        Criteria criteria = createCriteria(permissao);
        List<Permissao> listaPesquisa = criteria.list();
        return listaPesquisa;
    }

    public Permissao salvarPermissao(Permissao permissao) throws Exception {

        if (permissao.getIdPermissao() != null) {
            return this.atualizarPermissao(permissao);
        } else {
            return this.adicionarPermissao(permissao);
        }
    }

    private Permissao adicionarPermissao(Permissao permissao) throws Exception {
        return addEntity(permissao);
    }

    private Permissao atualizarPermissao(Permissao permissao) throws Exception {
        return setEntity(permissao);
    }

    public Permissao getPermissaoById(Integer idPermissao) throws Exception {
        return getEntity(Permissao.class, idPermissao);
    }

    public void deletarPermissao(Permissao permissao) throws Exception {
        removeEntity(permissao);
    }

    public List<Permissao> getListaPermissoesByPerfil(Perfil perfil) {
        Query query = getEntityManager().createQuery("SELECT p.listaPermissoes FROM Perfil AS p WHERE p = :perfil ");
        query.setParameter("perfil", perfil);

        return query.getResultList();
    }

}
