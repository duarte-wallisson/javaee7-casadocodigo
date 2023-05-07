package org.casadocodigo.store.daos;

import org.casadocodigo.store.models.Book;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;


@Dependent
public class BookDAO {
    @PersistenceContext
    private EntityManager manager;

    public void save(Book product) {
        manager.persist(product);
    }

    public List<Book> list() {
        return manager.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class).getResultList();
    }
}
