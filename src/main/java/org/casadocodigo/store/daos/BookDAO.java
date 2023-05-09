package org.casadocodigo.store.daos;

import org.casadocodigo.store.models.Book;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;


public class BookDAO {
    @PersistenceContext
    private EntityManager manager;

    public BookDAO() {

    }

    public BookDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Book product) {
        manager.persist(product);
    }

    public List<Book> list() {
        TypedQuery<Book> query = manager.createQuery("select b from Book b join fetch b.authors", Book.class).setMaxResults(3);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        return query.getResultList();
    }

    public List<Book> lastReleases() {
        return manager.createQuery("select b from Book b where b.releaseDate <= now() order by b.id desc", Book.class)
                .setMaxResults(3).getResultList();
    }

    public List<Book> last(int number) {
        TypedQuery<Book> query = manager.createQuery("select b from Book b join fetch b.authors", Book.class).setMaxResults(number);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        return query.getResultList();
    }

    public Book findById(Integer id) {
        return manager.find(Book.class, id);
    }
}
