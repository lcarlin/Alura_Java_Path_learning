package br.com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	private Integer id ; 
	private String nome ; 
	private List<Produto>  produtos = new ArrayList<Produto>();
	public Object getProdutos;

	public Categoria (Integer id , String nome  ) {
		// TODO Auto-generated constructor stub

		this.nome = nome ; 
		this.id = id  ; 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void adicionar(Produto produto) {
		// TODO Auto-generated method stub
		produtos.add(produto);
		
	}
	
	public List<Produto> getProdutos() {
		return produtos ;
	}
	
}
