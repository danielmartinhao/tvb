package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import dao.IUsuarioDAO;
import entities.Usuario;

@Named
@RequestScoped
public class LoginBean {

	@Inject
	EntityManager em;
	
	@Inject
	UserTransaction ut;
	
	@Inject
	IUsuarioDAO usuarioDao;

	@Inject 
	FacesContext context;
	
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
//		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = usuarioDao.getForLogin(this.login);
		if (usuario != null) {
			try {
//				ut.begin();
				System.out.println(usuario.getPermissaos().toString());
				System.out.println("teste");
//				ut.commit();
			} catch (Exception e){
				context.addMessage("failed", new FacesMessage(e.getMessage()));
				e.printStackTrace();
			}
			context.addMessage("generalMessages", new FacesMessage("Login executado!!!", "Bem-vindo " + usuario.getUsunome()));
		} else {
			context.addMessage("failed", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha de login.", "Usuário Inválido."));
		}
	}
}
