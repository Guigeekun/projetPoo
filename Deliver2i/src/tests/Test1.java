/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author guigeek
 */
public class Test1 {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
        final EntityManager em = emf.createEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                // creation d’une entite persistante
              //     em.persist(args);
                et.commit();
            } catch (Exception ex) {
                et.rollback();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
