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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
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
    @Inject
    private FacesContext facesContext;


    @Transactional
    public void checkout() throws IOException {
        systemUserDAO.save(systemUser);
        Checkout checkout = new Checkout(systemUser,cart);
        checkoutDAO.save(checkout);

        String contextName = facesContext.getExternalContext().getRequestContextPath();
//        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//        response.setHeader("Location", "/"+contextName+"/services/payment?uuid="+checkout.getUuid());

    }

}


