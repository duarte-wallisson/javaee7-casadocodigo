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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
