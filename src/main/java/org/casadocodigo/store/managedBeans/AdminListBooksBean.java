package org.casadocodigo.store.managedBeans;

import org.casadocodigo.store.daos.BookDAO;
import org.casadocodigo.store.models.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
public class AdminListBooksBean {
    @Inject
    private BookDAO bookDAO;
    private List<Book> books = new ArrayList<Book>();

    @PostConstruct
    private void loadObjects() {
        this.books = bookDAO.list();
    }

    public List<Book> getBooks() {
        return books;
    }
}
