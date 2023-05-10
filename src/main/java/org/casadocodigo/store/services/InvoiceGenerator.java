package org.casadocodigo.store.services;

import org.casadocodigo.store.models.Checkout;
import org.casadocodigo.store.models.InvoiceData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

public class InvoiceGenerator {

	public void invoiceFor(Checkout checkout) {
		Client client = ClientBuilder.newClient();
		InvoiceData invoiceData = new InvoiceData(checkout);
		String uriToGenerateInvoice = "http://book-payment.herokuaorg/invoice";
		Entity<InvoiceData> json = Entity.json(invoiceData);
		client.target(uriToGenerateInvoice).request().post(json, String.class);		
	}

	
}
