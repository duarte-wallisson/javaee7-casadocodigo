package org.casadocodigo.store.resources;

import org.casadocodigo.store.daos.BookDAO;
import org.casadocodigo.store.models.Book;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

@Path("books")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BooksResource {
    @Inject
    private BookDAO bookDAO;

    @GET
    @Wrapped(element="books")
    public List<Book> lastBooks() { return bookDAO.lastReleases(); }
}