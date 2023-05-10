package org.casadocodigo.store.infra;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@ApplicationScoped
public class MailSender {
    @Resource(mappedName = "java:jboss/mail/gmail")
    private Session session;
    public void send(String from,String to,String subject, String body){
        Message mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setSubject("Sua compra foi registrada");
            mimeMessage.setContent(body,"text/html");
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}