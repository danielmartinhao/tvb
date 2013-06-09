package br.com.vivabem.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer usuoid;

	private String usulogin;

	private String usunome;

	private String ususenha;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="usugruoid")
	private Grupo grupo;

	//bi-directional many-to-many association to Permissao
	@ManyToMany(mappedBy="usuarios", fetch=FetchType.EAGER)
	private List<Permissao> permissaos;

	public Usuario() {
	}

	public Integer getUsuoid() {
		return this.usuoid;
	}

	public void setUsuoid(Integer usuoid) {
		this.usuoid = usuoid;
	}

	public String getUsulogin() {
		return this.usulogin;
	}

	public void setUsulogin(String usulogin) {
		this.usulogin = usulogin;
	}

	public String getUsunome() {
		return this.usunome;
	}

	public void setUsunome(String usunome) {
		this.usunome = usunome;
	}

	public String getUsusenha() {
		return this.ususenha;
	}

	public void setUsusenha(String ususenha) {
		this.ususenha = ususenha;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Permissao> getPermissaos() {
		return this.permissaos;
	}

	public void setPermissaos(List<Permissao> permissaos) {
		this.permissaos = permissaos;
	}

}