package org.casadocodigo.store.daos;

import org.casadocodigo.store.models.Book;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Dependent
public class BookDAO {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public void save(Book product) {
        manager.persist(product);
    }
}
