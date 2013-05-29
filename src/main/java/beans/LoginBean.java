package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean {

	private String login;
	private String senha;
	
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

	public void executarLogin(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, new FacesMessage("Login executado!!!", "Bem-vindo " + this.login));
		context.addMessage(null, new FacesMessage("Teste", "Teste"));
		
	}

}
