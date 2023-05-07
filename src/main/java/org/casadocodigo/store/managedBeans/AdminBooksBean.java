package org.casadocodigo.store.managedBeans;

import lombok.Getter;
import lombok.Setter;
import org.casadocodigo.store.daos.AuthorDAO;
import org.casadocodigo.store.daos.BookDAO;
import org.casadocodigo.store.infra.MessagesHelper;
import org.casadocodigo.store.models.Author;
import org.casadocodigo.store.models.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Model
public class AdminBooksBean {
    @Getter
    @Setter
    private Book product = new Book();
    @Inject
    private BookDAO bookDAO = new BookDAO();
    @Inject
    private AuthorDAO authorDAO = new AuthorDAO();
    @Getter
    private List<Author> authors = new ArrayList<>();
    @Getter
    @Setter
    private Part summary;

    @Inject
    private MessagesHelper messagesHelper;

    @PostConstruct
    public void loadObjects() {
        this.authors = authorDAO.list();
    }

    @Transactional
    public String save() {
        System.out.println(summary.getName() + ";"
                + summary.getHeader("content-disposition"));


        if (messagesHelper.hasMessages()) {
            return "/livros/form";
        }

        bookDAO.save(product);

        messagesHelper.addFlash(new FacesMessage("Livro gravado com sucesso"));
        clearObjects();
        return "/livros/lista?faces-redirect=true";
    }

    private void clearObjects() {
        this.product = new Book();
    }
}
