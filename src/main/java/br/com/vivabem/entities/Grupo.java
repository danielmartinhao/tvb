package br.com.vivabem.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer gruoid;

	private String grudesc;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="grupo")
	private List<Usuario> usuarios;

	//bi-directional many-to-many association to Permissao
	@ManyToMany(mappedBy="grupos")
	private List<Permissao> permissaos;

	public Grupo() {
	}

	public Integer getGruoid() {
		return this.gruoid;
	}

	public void setGruoid(Integer gruoid) {
		this.gruoid = gruoid;
	}

	public String getGrudesc() {
		return this.grudesc;
	}

	public void setGrudesc(String grudesc) {
		this.grudesc = grudesc;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setGrupo(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setGrupo(null);

		return usuario;
	}

	public List<Permissao> getPermissaos() {
		return this.permissaos;
	}

	public void setPermissaos(List<Permissao> permissaos) {
		this.permissaos = permissaos;
	}

}