/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.imunita.vacinasweb.controller.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.*;
import javax.persistence.*;

/**
 *
 * @author Rodolpho
 */
@ApplicationScoped
public class EntityManagerProduces {

    private final EntityManagerFactory factory;

    public EntityManagerProduces() {
        factory = Persistence.createEntityManagerFactory("VacinasWeb_PU");
    }

    @Produces
    @RequestScoped
    public EntityManager criaEntityManager() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        return manager;
    }

    public void finalizaEntityManager(@Disposes EntityManager manager) {
        try {
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Erro ao persistir no Banco:" + e.getMessage());
        } finally {
            manager.close();
        }
    }

}
