package br.com.vivabem.testes;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketbox.core.UserContext;
import org.picketbox.core.ctx.SecurityContext;

import br.com.vivabem.resources.SecurityContextDeUsuarioLogado;

@Named
@RequestScoped
public class BeanDeTeste {
	
	private String usuarioLogado;

	@Inject @SecurityContextDeUsuarioLogado
	private SecurityContext securityContext;
	
	public BeanDeTeste() {
		
	}
	
	@PostConstruct
	public void intial() {
		UserContext userContext = null;
		userContext = securityContext.getUserContext();
		this.usuarioLogado = userContext.getPrincipal().getName();
//		try {
////			SecurityContext securityContext = SecurityContextPropagation.getContext();
//		} catch (ProcessingException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
		this.usuarioLogado = userContext.getPrincipal().getName();
	}

	public String getUsuarioLogado() {
		
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
