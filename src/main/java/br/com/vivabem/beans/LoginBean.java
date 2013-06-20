package br.com.vivabem.beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.picketlink.Identity;
import org.picketlink.extensions.core.pbox.LoginCredential;
import org.picketlink.idm.credential.internal.UsernamePasswordCredentials;

import br.com.vivabem.dao.IUsuarioDAO;
import br.com.vivabem.entities.Usuario;
import br.com.vivabem.util.LoginUtil;

@Named
@RequestScoped
public class LoginBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	IUsuarioDAO usuarioDao;

	@Inject 
	FacesContext context;
	
	@Inject
	Identity identity;
	
	@Inject
	LoginCredential credential;
	
	@Inject @MeuUsuarioLogado
	private Instance<Usuario> usuario;
	
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
	
	public void loginFromJSF(String username, String password) {
		//this.credential.setCredential(new UsernamePasswordCredentials(username, password));
		
		
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.login(this.login, LoginUtil.getHashMD5(this.senha));
			System.out.println("to aqui...");
			this.logado = true;
//			context.addMessage("generalMessages", new FacesMessage("Login executado!!!", "Bem-vindo " + getUsuario().getUsunome()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			e.printStackTrace();
			context.addMessage("falied", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha de login", "Usuário Inválido."));
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
