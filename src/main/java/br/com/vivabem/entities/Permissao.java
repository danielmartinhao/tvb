package br.com.vivabem.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the permissao database table.
 * 
 */
@Entity
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer peroid;

	private String perdesc;

	//bi-directional many-to-many association to Grupo
	@ManyToMany
	@JoinTable(
		name="permissao_grupo"
		, joinColumns={
			@JoinColumn(name="pgrperoid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="pgrgruoid")
			}
		)
	private List<Grupo> grupos;

	//bi-directional many-to-many association to Usuario
	@ManyToMany
	@JoinTable(
		name="permissao_usuario"
		, joinColumns={
			@JoinColumn(name="pusperoid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="pususuoid")
			}
		)
	private List<Usuario> usuarios;

	public Permissao() {
	}

	public Integer getPeroid() {
		return this.peroid;
	}

	public void setPeroid(Integer peroid) {
		this.peroid = peroid;
	}

	public String getPerdesc() {
		return this.perdesc;
	}

	public void setPerdesc(String perdesc) {
		this.perdesc = perdesc;
	}

	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}