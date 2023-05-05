package org.casadocodigo.store.infra;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Iterator;

public class MessagesHelper {
    @Inject
    private FacesContext facesContext;

    public void addFlash(FacesMessage facesMessage) {
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null, facesMessage);
    }

    public void addMessage(FacesMessage facesMessage) {
        facesContext.addMessage(null, facesMessage);
    }

    public boolean hasMessages() {
        Iterator<FacesMessage> messages = facesContext.getMessages();
        return messages.hasNext();
    }
}

