package org.casadocodigo.store.listeners.checkout;

import org.casadocodigo.store.daos.CheckoutDAO;
import org.casadocodigo.store.infra.MailSender;
import org.casadocodigo.store.models.Checkout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationLookup", 
				propertyValue = "java:/jms/topics/checkoutsTopic") 
		})
public class SendCheckoutEmailListener implements MessageListener {
	
	private Logger logger = LoggerFactory.getLogger(SendCheckoutEmailListener.class);
	@Inject
	private MailSender mailSender;
	@Inject
	private CheckoutDAO checkoutDao;

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
			Checkout checkout = checkoutDao.findByUuid(text.getText());
			String emailBody = "<html><body>Compra realizada com sucesso. O código de acompanhamento é "
					+ checkout.getUuid() + "</body></html>";
			
			mailSender.send("wallisson.elduarte@gmail.com", checkout.getBuyer()
					.getEmail(), "Sua compra foi registrada com sucesso",
					emailBody);

		} catch (Exception e) {
			logger.error("Problema no envio do email",e);
		}
	}

}
