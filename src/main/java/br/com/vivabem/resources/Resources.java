package br.com.vivabem.resources;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.picketbox.core.PicketBoxManager;
import org.picketbox.core.ctx.SecurityContext;
import org.picketbox.http.PicketBoxHTTPManager;
import org.picketbox.http.config.HTTPConfigurationBuilder;
import org.picketbox.http.config.PicketBoxHTTPConfiguration;

import br.com.vivabem.dao.IUsuarioDAO;
import br.com.vivabem.entities.Usuario;
import br.com.vivabem.sec.CustomConfigurationProvider;

public class Resources {
	// Expose an entity manager using the resource producer pattern
	@PersistenceContext
	@Produces
	private EntityManager em; //

	@Inject
	IUsuarioDAO usuarioDao;
	
	@Inject
	FacesContext context;

	@Produces
	Logger getLogger(InjectionPoint ip) { //
		String category = ip.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}

	@Produces
	@RequestScoped
	FacesContext getFacesContext() { //
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@MeuUsuarioLogado
	Usuario getUsuarioDoSistema() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return usuarioDao.getForLogin(request.getUserPrincipal().getName());
	}

	@Produces
	@SecurityContextDeUsuarioLogado
	SecurityContext securityContext;

	@Produces
	@SessionScoped
	PicketBoxManager getPicketBoxManager() {
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext(); 
		HTTPConfigurationBuilder builder = (new CustomConfigurationProvider()).getBuilder(servletContext);
		PicketBoxHTTPConfiguration configuration = (PicketBoxHTTPConfiguration) builder.build();
		PicketBoxManager picketBoxManager = new PicketBoxHTTPManager(configuration);
		return picketBoxManager;
	}

	// @Produces @SecurityContextDeUsuarioLogado
	// SecurityContext getSecurityContextDeUsuarioLogado() {
	// SecurityContext securityContext = null;
	// try {
	// securityContext = SecurityContextPropagation.getContext();
	// } catch (ProcessingException e) {
	// System.out.println(e.getMessage());
	// e.printStackTrace();
	// }
	// return securityContext;
	// }
}
