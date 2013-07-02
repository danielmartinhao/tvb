package br.com.vivabem.util;

import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.picketbox.core.PicketBoxManager;
import org.picketbox.core.UserContext;
import org.picketbox.core.UserCredential;
import org.picketbox.core.authentication.credential.UsernamePasswordCredential;
import org.picketbox.core.ctx.PicketBoxSecurityContext;
import org.picketbox.core.ctx.SecurityContext;
import org.picketbox.core.ctx.SecurityContextPropagation;
import org.picketbox.core.exceptions.AuthenticationException;
import org.picketbox.http.HTTPUserContext;

import br.com.vivabem.resources.SecurityContextDeUsuarioLogado;

public class UtilPicketBox {
	@Inject
	private static Instance<PicketBoxManager> pbm;

	@Inject
	private static FacesContext context;

	@Inject @SecurityContextDeUsuarioLogado
	private static Instance<SecurityContext> secContext;

	private UtilPicketBox() {

	}

	public static void authenticate(String login, String password) {
		PicketBoxManager picketBoxManager = pbm.get();
		picketBoxManager.start();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		UserCredential credential = new UsernamePasswordCredential("admin","admin");
		HTTPUserContext authenticatingContext = new HTTPUserContext(request, response, credential);
		try {
			UserContext authenticatedContext = picketBoxManager.authenticate(authenticatingContext);
			SecurityContext securityContext = secContext.get();
			securityContext = new PicketBoxSecurityContext(authenticatedContext);
			SecurityContextPropagation.setContext(securityContext);
		} catch (AuthenticationException au ) {
			context.addMessage("falied", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha de login", "Usuário Inválido."));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
