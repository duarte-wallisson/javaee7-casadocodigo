package org.casadocodigo.store.managedBeans.site;

import lombok.Getter;
import lombok.Setter;
import org.casadocodigo.store.daos.BookDAO;
import org.casadocodigo.store.models.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Model
public class ProductDetailBean {
    @PersistenceContext
    private EntityManager manager;
    @Inject
    private BookDAO bookDAO;
    private Book book;
    @Getter
    @Setter
    private Integer id;
    @PostConstruct
    private void loadManager(){
        this.bookDAO = new BookDAO(manager);
    }

    public void loadBook(){
        this.book = bookDAO.findById(id);
    }
    public Book getBook() {
        return book;
    }
}
