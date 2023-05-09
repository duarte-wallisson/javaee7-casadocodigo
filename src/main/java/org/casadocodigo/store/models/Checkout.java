package org.casadocodigo.store.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Checkout {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private SystemUser buyer;
    private BigDecimal value;
    private String jsonCart;

    protected Checkout() {
    }
    public Checkout(SystemUser user, ShoppingCart cart) {
        this.buyer = user;
        this.value = cart.getTotal();
        this.jsonCart = cart.toJson();
    }

}
