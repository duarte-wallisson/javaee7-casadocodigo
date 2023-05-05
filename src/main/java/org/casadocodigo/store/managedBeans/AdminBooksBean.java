package org.casadocodigo.store.managedBeans;

import org.casadocodigo.store.daos.AuthorDAO;
import org.casadocodigo.store.daos.BookDAO;
import org.casadocodigo.store.infra.MessagesHelper;
import org.casadocodigo.store.models.Author;
import org.casadocodigo.store.models.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Model
public class AdminBooksBean {
    private Book product = new Book();
    @Inject
    private BookDAO bookDAO = new BookDAO();
    @Inject
    private AuthorDAO authorDAO = new AuthorDAO();
    private List<Author> authors = new ArrayList<Author>();
    private List<Integer> selectedAuthorsIds = new ArrayList<>();

    @Inject
    private MessagesHelper messagesHelper;
    ;


    public Book getProduct() {
        return product;
    }

    public List<Integer> getSelectedAuthorsIds() {
        return selectedAuthorsIds;
    }

    public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
        this.selectedAuthorsIds = selectedAuthorsIds;
    }

    @PostConstruct
    public void loadObjects() {
        this.authors = authorDAO.list();
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Transactional
    public String save() {
        populateBookAuthor();

        if(product.getTitle()==null || product.getTitle().trim().isEmpty()){
            messagesHelper.addMessage(new FacesMessage("titulo obrigatorio"));
        }

        if(product.getDescription()==null || product.getDescription().trim().isEmpty()){
            messagesHelper.addMessage(new FacesMessage("descrição obrigatoria"));
        }

        if(messagesHelper.hasMessages()){
            return "/livros/form";
        }


        bookDAO.save(product);


        messagesHelper.addFlash(new FacesMessage("Livro gravado com sucesso"));
        clearObjects();
        return "/livros/lista?faces-redirect=true";
    }

    private void clearObjects() {
        this.product = new Book();
        this.selectedAuthorsIds.clear();
    }


    private void populateBookAuthor() {
        selectedAuthorsIds.stream().map(Author::new).forEach(product::add);
    }

}
