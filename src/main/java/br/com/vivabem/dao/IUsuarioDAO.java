package br.com.vivabem.dao;

import br.com.vivabem.entities.Usuario;

public interface IUsuarioDAO {
 	public Usuario getForLogin(String login);
 	public void addUser(Usuario usuario);
}