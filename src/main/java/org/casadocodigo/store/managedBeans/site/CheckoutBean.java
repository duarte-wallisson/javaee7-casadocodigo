package org.casadocodigo.store.managedBeans.site;


import lombok.Getter;
import lombok.Setter;
import org.casadocodigo.store.daos.CheckoutDAO;
import org.casadocodigo.store.daos.SystemUserDAO;
import org.casadocodigo.store.models.Checkout;
import org.casadocodigo.store.models.ShoppingCart;
import org.casadocodigo.store.models.SystemUser;
import org.casadocodigo.store.services.PaymentGateway;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;

@Model
public class CheckoutBean {
    @Getter
    @Setter
    private SystemUser systemUser = new SystemUser();
    @Inject
    private SystemUserDAO systemUserDAO;
    @Inject
    private CheckoutDAO checkoutDAO;
    @Inject
    private ShoppingCart cart;
    @Inject
    private PaymentGateway paymentGateway;


    @Transactional
    public void checkout() throws IOException {
        systemUserDAO.save(systemUser);
        Checkout checkout = new Checkout(systemUser,cart);
        checkoutDAO.save(checkout);

        paymentGateway.pay(checkout.getValue());
    }

}


