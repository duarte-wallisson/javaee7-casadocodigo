package org.casadocodigo.store.managedBeans.site;

import org.casadocodigo.store.daos.BookDAO;
import org.casadocodigo.store.models.Book;
import org.casadocodigo.store.models.ShoppingCart;
import org.casadocodigo.store.models.ShoppingItem;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ShoppingCartBean {

    @Inject
    private ShoppingCart shoppingCart;
    @Inject
    private BookDAO bookDAO;

    public String add(Integer id){
        Book book = bookDAO.findById(id);
        ShoppingItem item = new ShoppingItem(book);
        shoppingCart.add(item);
        return "/site/carrinho?faces-redirect=true";
    }

    public String remove(Integer id){
        Book book = bookDAO.findById(id);
        ShoppingItem item = new ShoppingItem(book);
        shoppingCart.remove(item);
        return "/site/carrinho?faces-redirect=true";
    }
}
