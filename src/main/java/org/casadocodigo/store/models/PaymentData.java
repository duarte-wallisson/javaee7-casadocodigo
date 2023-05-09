package org.casadocodigo.store.models;

import lombok.Getter;

import java.math.BigDecimal;

public class PaymentData {
    @Getter
    private BigDecimal value;
    public PaymentData() {

    }
    public PaymentData(BigDecimal value) {
        this.value = value;
    }
}
