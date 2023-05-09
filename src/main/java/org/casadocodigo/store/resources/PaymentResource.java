package org.casadocodigo.store.resources;

import org.casadocodigo.store.daos.CheckoutDAO;
import org.casadocodigo.store.models.Checkout;
import org.casadocodigo.store.services.PaymentGateway;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.math.BigDecimal;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("payment")
public class PaymentResource {
    @Inject
    private CheckoutDAO checkoutDAO;
    @Inject
    private PaymentGateway paymentGateway;
    @Resource(name = "java:comp/DefaultManagedExecutorService")
    private ManagedExecutorService managedExecutorService;
    @Context
    private ServletContext ctx;


    @POST
    public void pay(@Suspended final AsyncResponse ar,
                    @QueryParam("uuid") String uuid) {
        String contextPath = ctx.getContextPath();
        Checkout checkout = checkoutDAO.findByUuid(uuid);
        managedExecutorService.submit(() -> {
            BigDecimal total = checkout.getValue();
            try {
                paymentGateway.pay(total);
                URI redirectURI = UriBuilder
                        .fromUri(contextPath + "/site/index.xhtml")
                        .queryParam("msg", "Compra realizada com sucesso")
                        .build();
                Response response = Response
                        .seeOther(redirectURI).build();
                ar.resume(response);
            } catch (Exception exception) {
                ar.resume(new WebApplicationException(exception));
            }
        });
    }
}
