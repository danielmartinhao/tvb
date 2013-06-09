package br.com.vivabem.beans;

import javax.enterprise.inject.Instance;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.com.vivabem.entities.Usuario;


@ManagedBean
public class BeanGeral {

	@Inject @MeuUsuarioLogado
	private Instance<Usuario> meuUsuarioLogado;

	private Usuario user;
	
	public BeanGeral() {
	
	}
	public Usuario getUser() {
		user = meuUsuarioLogado.get();
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}

}
