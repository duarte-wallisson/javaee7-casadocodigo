package org.casadocodigo.store.daos;

import org.casadocodigo.store.models.SystemUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SystemUserDAO {
    @PersistenceContext
    private EntityManager entityManager;
    public void save(SystemUser systemUser) {
        entityManager.persist(systemUser);
    }
}
