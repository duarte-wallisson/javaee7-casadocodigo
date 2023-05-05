package org.casadocodigo.store.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private int numberOfPages;
    private BigDecimal price;

    @OneToMany
    private List<Author> authors = new ArrayList<Author>();


    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", description=" + description + ", numberOfPages="
                + numberOfPages + ", price=" + price + "]";
    }


    public void add(Author author) {
        authors.add(author);
    }


}
