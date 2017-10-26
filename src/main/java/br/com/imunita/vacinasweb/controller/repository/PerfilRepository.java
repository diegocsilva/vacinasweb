/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Perfil;
import br.com.imunita.vacinasweb.model.entity.Permissao;
import br.com.imunita.vacinasweb.model.entity.Usuario;
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
public class PerfilRepository extends GenericRepository {

    public List<Perfil> getListaPerfisFake(Integer gerarNumero) {
        Perfil perfil;
        List<Perfil> listaRetorno = new ArrayList<>();
        for (int i = 0; i < gerarNumero; i++) {
            perfil = new Perfil();

            perfil.setIdPerfil(i);
            perfil.setAtivo(GeradorRandom.gerarBoolean());
            perfil.setDescricao(GeradorRandom.gerarTexto(10));
            perfil.setNome(GeradorRandom.gerarTexto(3).toUpperCase());

            listaRetorno.add(perfil);
        }

        return listaRetorno;
    }

    public List<Perfil> getListaTodosPerfils() {
        return findAll(Perfil.class);
    }

    public Perfil salvarPerfil(Perfil perfil) throws Exception {

        if (perfil.getIdPerfil() != null) {
            return this.atualizarPerfil(perfil);
        } else {
            return this.adicionarPerfil(perfil);
        }
    }

    private Perfil adicionarPerfil(Perfil perfil) throws Exception {
        return addEntity(perfil);
    }

    private Perfil atualizarPerfil(Perfil perfil) throws Exception {
        return setEntity(perfil);
    }

    public Perfil getPerfilById(Integer idPerfil) throws Exception {
        return getEntity(Perfil.class, idPerfil);
    }

    public List<Perfil> getListByFilters(Perfil perfil) {
        Criteria criteria = createCriteria(perfil);

        for (Permissao permissao : perfil.getListaPermissoes()) {
            criteria = updateCriteria(criteria, permissao);
        }
        List<Perfil> listaPesquisa = criteria.list();
        return listaPesquisa;
    }

    public void deletarPerfil(Perfil perfil) throws Exception {
        removeEntity(perfil);
    }

    public List<Perfil> getListaPerfisByUsuario(Usuario usuario) {
        Query query = getEntityManager().createQuery("SELECT u.listaPerfis FROM Usuario AS u WHERE u = :usuario ");
        query.setParameter("usuario", usuario);
        return query.getResultList();
    }

}
