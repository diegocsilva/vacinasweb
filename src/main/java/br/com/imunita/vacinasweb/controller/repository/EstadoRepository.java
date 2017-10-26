/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.controller.utils.GeradorRandom;
import br.com.imunita.vacinasweb.model.entity.Estado;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Criteria;

/**
 *
 * @author Diego
 */
@RequestScoped
public class EstadoRepository extends GenericRepository {

    public List<Estado> getListaEstadoFake(Integer gerarNumero) {
        Estado estado;
        List<Estado> listaRetorno = new ArrayList<>();
        for (int i = 0; i < gerarNumero; i++) {
            estado = new Estado();

            estado.setIdEstado(i);
            estado.setSigla(GeradorRandom.gerarTexto(1));
            estado.setNome(GeradorRandom.gerarTexto(3).toUpperCase());
            listaRetorno.add(estado);
        }

        return listaRetorno;
    }

    public List<Estado> getListaTodosEstados() {
        return findAll(Estado.class);
    }

    public List<Estado> getListByFilters(Estado estado) {
        Criteria criteria = createCriteria(estado);
        List<Estado> listaPesquisa = criteria.list();
        return listaPesquisa;
    }

    public Estado salvarEstado(Estado estado) throws Exception {

        if (estado.getIdEstado() != null) {
            return this.atualizarEstado(estado);
        } else {
            return this.adicionarEstado(estado);
        }
    }

    private Estado adicionarEstado(Estado estado) throws Exception {
        return addEntity(estado);
    }

    private Estado atualizarEstado(Estado estado) throws Exception {
        return setEntity(estado);
    }

    public Estado
            getEstadoById(Integer idEstado) throws Exception {
        return getEntity(Estado.class, idEstado);
    }

    public Estado buscaEstado(Estado estado) throws Exception {
        return getEntity(Estado.class, estado.getIdEstado());
    }

    public void deletarEstado(Estado estado) throws Exception {
        removeEntity(estado);
    }
}
