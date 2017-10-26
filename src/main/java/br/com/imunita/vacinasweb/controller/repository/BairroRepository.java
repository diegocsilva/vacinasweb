/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Bairro;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Criteria;

/**
 *
 * @author Diego
 */
@RequestScoped
public class BairroRepository extends GenericRepository {

    public List<Bairro> getListaBairroFake(Integer gerarNumero) {
        Bairro bairro;
        List<Bairro> listaRetorno = new ArrayList<>();
        for (int i = 0; i < gerarNumero; i++) {
            bairro = new Bairro();

            bairro.setIdBairro(i);
//            bairro.setEstado(GeradorRandom.gerarTexto(1));
            bairro.setNome(GeradorRandom.gerarTexto(3).toUpperCase());
            listaRetorno.add(bairro);
        }

        return listaRetorno;
    }

    public List<Bairro> getListaTodosBairros() {
        return findAll(Bairro.class);
    }

    public Bairro salvarBairro(Bairro bairro) throws Exception {

        if (bairro.getIdBairro() != null) {
            return this.atualizarBairro(bairro);
        } else {
            return this.adicionarBairro(bairro);
        }
    }

    private Bairro adicionarBairro(Bairro bairro) throws Exception {
        return addEntity(bairro);
    }

    private Bairro atualizarBairro(Bairro bairro) throws Exception {
        return setEntity(bairro);
    }

    public Bairro getBairroById(Integer idBairro) throws Exception {
        return getEntity(Bairro.class, idBairro);
    }

    public void deletarBairro(Bairro bairro) throws Exception {
        removeEntity(bairro);
    }

    public List<Bairro> getListByFilters(Bairro bairro) {
        Criteria criteria = createCriteria(bairro);
        criteria = updateCriteria(criteria, bairro.getCidade());
        List<Bairro> listaPesquisa = criteria.list();
        return listaPesquisa;
    }

    public List<Bairro> filtrarBairros(Bairro bairro) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b FROM Bairro AS b ");
        sql.append("WHERE b.idBairro IS NOT NULL ");
        if (bairro != null) {
            if (bairro.getCidade() != null && bairro.getCidade().getIdCidade() != null) {
                sql.append("AND b.cidade.idCidade = ").append(bairro.getCidade().getIdCidade());
            }
            if (bairro.getNome() != null) {
                sql.append("AND b.nome LIKE '%").append(bairro.getNome()).append("%'");
            }
        }
        return getEntityManager().createQuery(sql.toString()).getResultList();
    }

}
