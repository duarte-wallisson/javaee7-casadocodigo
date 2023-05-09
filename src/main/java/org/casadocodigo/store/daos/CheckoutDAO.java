package org.casadocodigo.store.daos;

import org.casadocodigo.store.models.Checkout;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CheckoutDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Checkout checkout) {
        entityManager.persist(checkout);
    }
}
