package dao;

import entities.Usuario;

public interface IUsuarioDAO {
 	public Usuario getForLogin(String login);
 	public void addUser(Usuario usuario);
}