package org.casadocodigo.store.managedBeans.site;

import org.casadocodigo.store.daos.SystemUserDAO;
import org.casadocodigo.store.models.ShoppingCart;
import org.casadocodigo.store.models.SystemUser;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;

@Model
public class CheckoutBean {
    private SystemUser systemUser = new SystemUser();
    @Inject
    private SystemUserDAO systemUserDAO;
    @Inject
    private ShoppingCart cart;

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    @Transactional
    public void checkout() throws IOException {
        systemUserDAO.save(systemUser);
        //vamos também gravar a compra
        //aprovar com um sistema externo
    }
}


