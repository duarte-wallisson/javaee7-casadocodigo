package org.casadocodigo.store.resources;

import org.casadocodigo.store.daos.CheckoutDAO;
import org.casadocodigo.store.infra.MailSender;
import org.casadocodigo.store.models.Checkout;
import org.casadocodigo.store.services.InvoiceGenerator;
import org.casadocodigo.store.services.PaymentGateway;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
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
    @Inject
    private JMSContext jmsContext;
    @Inject
    private MailSender mailSender;
    @Inject
    private InvoiceGenerator invoiceGenerator;
    @Resource(name = "java:/jms/topics/checkoutsTopic")
    private Destination checkoutsTopic;



    @POST
    public void pay(@Suspended final AsyncResponse ar,
                    @QueryParam("uuid") String uuid) {
        String contextPath = ctx.getContextPath();
        Checkout checkout = checkoutDAO.findByUuid(uuid);
        JMSProducer producer = jmsContext.createProducer();
        managedExecutorService.submit(() -> {
            BigDecimal total = checkout.getValue();
            try {
                paymentGateway.pay(total);

                producer.send(checkoutsTopic, checkout.getUuid());

                String mailBody = "Nova compra. Seu código de acompanhamento é "+checkout.getUuid();
                mailSender.send("wallisson.elduarte@gmail.com",
                        checkout.getBuyer().getEmail(),
                        "Nova compra", mailBody);

                invoiceGenerator.invoiceFor(checkout);

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
