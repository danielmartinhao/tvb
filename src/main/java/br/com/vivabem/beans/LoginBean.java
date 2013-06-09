package br.com.vivabem.beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.com.vivabem.HashPassword;
import br.com.vivabem.dao.IUsuarioDAO;
import br.com.vivabem.entities.Usuario;

@Named
@RequestScoped
public class LoginBean {

	@Inject
	IUsuarioDAO usuarioDao;

	@Inject 
	FacesContext context;
	
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
	
	public void loginFromJSF() {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.login(this.login, HashPassword.getHashMD5(this.senha));
			this.logado = true;
			context.addMessage("generalMessages", new FacesMessage("Login executado!!!", "Bem-vindo " + getUsuario().getUsunome()));
		} catch (Exception e) {
			context.addMessage("falied", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha de login", "Usuário Inválido."));
			System.out.println(e.getStackTrace());
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
