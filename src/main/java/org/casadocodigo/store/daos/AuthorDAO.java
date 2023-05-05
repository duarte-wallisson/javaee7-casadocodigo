package org.casadocodigo.store.daos;

import org.casadocodigo.store.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AuthorDAO {
    @PersistenceContext
    private EntityManager manager;

    public List<Author> list() {
        return manager.createQuery("select a from Author a order by a.name asc", Author.class).getResultList();
    }
}
