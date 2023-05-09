package org.casadocodigo.store.managedBeans.site;

import org.casadocodigo.store.daos.BookDAO;
import org.casadocodigo.store.models.Book;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class HomeBean {

    @Inject
    private BookDAO bookDao;

    public List<Book> lastReleases(){
        return bookDao.lastReleases();
    }

    public List<Book> olderBooks(){
        return bookDao.last(20);
    }

}