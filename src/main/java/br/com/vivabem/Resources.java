package br.com.vivabem;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import br.com.vivabem.beans.MeuUsuarioLogado;
import br.com.vivabem.dao.IUsuarioDAO;
import br.com.vivabem.entities.Usuario;

public class Resources {
	// Expose an entity manager using the resource producer pattern
    @PersistenceContext
    @Produces 
    private EntityManager em;                                        // 

    @Inject
	IUsuarioDAO usuarioDao;
    
    @Produces
    Logger getLogger(InjectionPoint ip) {                            // 
        String category = ip.getMember()
                            .getDeclaringClass()
                            .getName();
        return Logger.getLogger(category);
    }

    @Produces
    FacesContext getFacesContext() {                                 // 
        return FacesContext.getCurrentInstance();
    }
    
    @Produces @MeuUsuarioLogado public Usuario getUsuarioDoSistema() {
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	return usuarioDao.getForLogin(request.getUserPrincipal().getName());
	}
}
