package br.com.vivabem.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
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
import org.picketbox.http.PicketBoxHTTPManager;
import org.picketbox.http.config.HTTPConfigurationBuilder;
import org.picketbox.http.config.PicketBoxHTTPConfiguration;

import br.com.vivabem.dao.IUsuarioDAO;
import br.com.vivabem.entities.Usuario;
import br.com.vivabem.resources.MeuUsuarioLogado;
import br.com.vivabem.resources.SecurityContextDeUsuarioLogado;
import br.com.vivabem.sec.CustomConfigurationProvider;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	IUsuarioDAO usuarioDao;

	@Inject 
	FacesContext context;
	
	@Inject @MeuUsuarioLogado
	private Instance<Usuario> usuario;
	
	@Inject @SecurityContextDeUsuarioLogado
	private Instance<SecurityContext> secContext;
	
	private String login;
	private String senha;
	private boolean logado;																																									
	
	public LoginBean() {
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario.get();
	}

	public void setUsuario(Instance<Usuario> usuario) {
		this.usuario = usuario;
	}
	
	public void loginFromJSF() {
		//this.credential.setCredential(new UsernamePasswordCredentials(username, password));
//		try {
////			if (!this.identity.isLoggedIn()) {
////				this.loginCredentials.setUserId(login);
////				this.loginCredentials.setPassword(senha);
////				this.identity.login();
////			}
//			//request.login(this.login, LoginUtil.getHashMD5(this.senha));
//			
//			System.out.println("to aqui...");
//			this.logado = true;
////			context.addMessage("generalMessages", new FacesMessage("Login executado!!!", "Bem-vindo " + getUsuario().getUsunome()));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			System.out.println(e.getStackTrace());
//			e.printStackTrace();
//			context.addMessage("falied", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha de login", "Usuário Inválido."));
//		}
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext(); 
		HTTPConfigurationBuilder builder = (new CustomConfigurationProvider()).getBuilder(servletContext);
		PicketBoxHTTPConfiguration configuration = (PicketBoxHTTPConfiguration) builder.build();
		PicketBoxManager picketBoxManager = new PicketBoxHTTPManager(configuration);
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
	
	public void executarLogout() {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.logout();
			this.logado = false;
		} catch (Exception e) {
			context.addMessage("failed", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha de logout.", "Não foi possível executar o logout."));
		}
	}
	
	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	
}
