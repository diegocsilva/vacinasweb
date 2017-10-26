/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Cidade;
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
public class CidadeRepository extends GenericRepository {

    @Inject
    private EstadoRepository estadoRepository;

    public List<Cidade> getListaTodasCidades() {
        return findAll(Cidade.class);
    }

    public Cidade salvarCidade(Cidade cidade) throws Exception {

        if (cidade.getIdCidade() != null) {
            return this.atualizarCidade(cidade);
        } else {
            return this.adicionarCidade(cidade);
        }
    }

    private Cidade adicionarCidade(Cidade cidade) throws Exception {
        return addEntity(cidade);
    }

    private Cidade atualizarCidade(Cidade cidade) throws Exception {
        return setEntity(cidade);
    }

    public Cidade getCidadeById(Integer idCidade) throws Exception {
        return getEntity(Cidade.class, idCidade);
    }

    public List<Cidade> getListByFilters(Cidade cidade) {
        Criteria criteria = createCriteria(cidade);
        criteria = updateCriteria(criteria, cidade.getEstado());
        List<Cidade> listaPesquisa = criteria.list();
        return listaPesquisa;
    }

    public void deletarCidade(Cidade cidade) throws Exception {
        removeEntity(cidade);
    }

}
