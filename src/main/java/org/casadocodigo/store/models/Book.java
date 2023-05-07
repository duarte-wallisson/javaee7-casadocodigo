package org.casadocodigo.store.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    @Length(min = 10)
    private String description;

    @Min(50)
    private int numberOfPages;

    @DecimalMin("20")
    @NotNull
    private BigDecimal price;

    @ManyToMany
    @Size(min = 1)
    @NotNull
    private List<Author> authors = new ArrayList<Author>();

    @NotNull
    private Calendar releaseDate;

}
