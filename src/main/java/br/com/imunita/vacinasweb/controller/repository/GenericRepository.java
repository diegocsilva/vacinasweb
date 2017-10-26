/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import br.com.imunita.vacinasweb.controller.utils.GenericMessages;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

/**
 *
 * @author Rodolpho
 */
@RequestScoped
public abstract class GenericRepository implements Serializable {

    @Inject
    private GenericMessages messages;

    @Inject
    private EntityManager entityManager;

    public <T> List<T> findAll(Class<T> classToCast) {
        return getEntityManager().createQuery(("FROM " + classToCast.getName())).getResultList();
    }

    public <T> List<T> findByFilter(Class<T> classToCast, Object objeto1, Object objeto2) {
        return null;
    }

    public Criteria createCriteria(Object object) {
        Session session = (Session) getEntityManager().getDelegate();
        return session.createCriteria(object.getClass()).add(createExample(object));

    }

    public Criteria updateCriteria(Criteria criteria, Object object) {

        return criteria.createCriteria(object.getClass().getSimpleName().toLowerCase()).add(createExample(object));

    }

    public Criteria updateCriteria(Criteria criteria, List<?> listObject, String campo) {
        for (Object object : listObject) {
            criteria.createCriteria(campo).add(createExample(object));
        }
        return criteria;
    }

    private Example createExample(Object object) {
        Example example = Example.create(object)
                .enableLike(MatchMode.ANYWHERE)
                .ignoreCase()
                .excludeZeroes();
        return example;
    }

    public <T> List<T> findByFilter(Class<T> classToCast, HashMap<String, String> filters) {
        String sql = " FROM " + classToCast.getName() + " WHERE id" + classToCast.getName() + " IS NOT NULL";

        for (Map.Entry<String, String> filter : filters.entrySet()) {
            String key = filter.getKey();
            String value = filter.getValue();
            sql += " AND " + key;
            sql += " " + value;
        }
        return getEntityManager().createQuery((sql)).getResultList();
    }

    /**
     * Executa comandos no banco do tipo de atualizacao de dados: update, insert, delete
     *
     * @param query
     * @param values
     * @return geralmente retorna a quantidade de registros afetados, ou o objeto persistido
     */
    protected int executeCommand(String query, Object... values) {
        Query qr = createQuery(query, values);
        return qr.executeUpdate();
    }

    private Query createQuery(String query, Object... values) {
        Query qr = getEntityManager().createQuery(query);
        for (int i = 0; i < values.length; i++) {
            qr.setParameter(i, values[i]);
        }
        return qr;
    }

    protected <T> T addEntity(Object objeto) throws Exception {
        getEntityManager().persist(objeto);
        messages.addInfoMessage(objeto.getClass().getSimpleName() + " salvo com sucesso!");
        return (T) objeto;
    }

    protected <T> T setEntity(Object objeto) throws Exception {
        objeto = getEntityManager().merge(objeto);
        messages.addInfoMessage(objeto.getClass().getSimpleName() + " salvo com sucesso!");
        return (T) objeto;
    }

    protected <T> T getEntity(Class<T> classToCast, Integer id) throws Exception {
        return getEntityManager().find(classToCast, id);
    }

    protected <T> T getEntity(Class<T> classToCast, Long id) throws Exception {
        return getEntityManager().find(classToCast, id);
    }

    /**
     * deleta um objeto no banco de dados
     *
     * @param objeto
     */
    protected void removeEntity(Object objeto) throws Exception {
        Object objectMerge = getEntityManager().merge(objeto);
        getEntityManager().remove(objectMerge);
        messages.addInfoMessage(objeto.getClass().getSimpleName() + " deletado com sucesso!");
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
