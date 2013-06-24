package br.com.vivabem.testes;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.registry.infomodel.User;

import org.picketbox.core.UserContext;
import org.picketbox.core.ctx.SecurityContext;
import org.picketbox.core.ctx.SecurityContextPropagation;
import org.picketbox.core.exceptions.ProcessingException;

@Named
@RequestScoped
public class BeanDeTeste {
	
	private String usuarioLogado;

	public BeanDeTeste() {
		
	}
	
	@PostConstruct
	public void intial() {
		UserContext userContext = null;
		try {
			SecurityContext securityContext = SecurityContextPropagation.getContext();
			userContext = securityContext.getUserContext();
			this.usuarioLogado = userContext.getPrincipal().getName();
		} catch (ProcessingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getUsuarioLogado() {
		
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
