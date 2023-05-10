package org.casadocodigo.store.services;

import org.casadocodigo.store.models.PaymentData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.math.BigDecimal;

public class PaymentGateway {
    public PaymentGateway() {
    }
    public void pay(BigDecimal total) {
        Client client = ClientBuilder.newClient();
        PaymentData paymentData = new PaymentData(total);
        String uriToPay = "https://reqbin.com/echo/post/json";
        Entity<PaymentData> json = Entity.json(paymentData);
        client.target(uriToPay).request()
                .post(json, String.class);
    }
}
