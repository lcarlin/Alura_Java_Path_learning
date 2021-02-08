package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7872503757574608017L;
	@Id
	private String nome;

	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	public Role(String nome) {
		// TODO Auto-generated constructor stub
		this.nome = nome ;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nome ;
	} 

}
