package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import dao.IUsuarioDAO;
import entities.Permissao;
import entities.Usuario;

@Named
@RequestScoped
public class LoginBean {

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
		Usuario usuario = usuarioDao.getForLogin(this.login);
		if (usuario != null) {
//			for (Permissao p: usuario.getPermissaos()){
//				System.out.println(p.getPerdesc());
//			}
//			System.out.println(usuario.getGrupo().getGrudesc());
			context.addMessage("generalMessages", new FacesMessage("Login executado!!!", "Bem-vindo " + usuario.getUsunome()));
		} else {
			context.addMessage("failed", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha de login.", "Usuário Inválido."));
		}
	}
}
