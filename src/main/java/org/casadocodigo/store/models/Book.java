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
@Cacheable
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
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1)
    @NotNull
    private List<Author> authors = new ArrayList<>();
    @NotNull
    private Calendar releaseDate;

//    Temporário
    private String summaryPath = "https://s3.console.aws.amazon.com/s3/upload/casadocodigo-duarte?region=sa-east-1";
    private String coverPath = "";
}
