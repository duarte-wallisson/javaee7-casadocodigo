package org.casadocodigo.store.managedBeans;

import org.casadocodigo.store.daos.BookDAO;
import org.casadocodigo.store.models.Book;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;


@Model
public class AdminBooksBean {
    private Book product = new Book();
    @Inject
    private BookDAO bookDAO = new BookDAO();

    @Transactional
    public void save() {
        bookDAO.save(product);
    }

    public Book getProduct() {
        return product;
    }

    public void setProduct(Book product) {
        this.product = product;
    }

}
