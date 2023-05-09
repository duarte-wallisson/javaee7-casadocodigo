package org.casadocodigo.store.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class FinalShoppingItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Book book;
    private int quantity;
    private BigDecimal currentPrice;
}
