package br.com.alura.jdbc.modelo ;
public class Produto {

	private Integer id ; 
	private String nome ; 
	private String descricao;
	public Produto(String nome , String descricao ) {
		// TODO Auto-generated constructor stub
		super() ; 
		this.nome = nome ; 
		this.descricao = descricao ; 
	}
	public Produto(Integer id, String nome , String descricao ) {
		// TODO Auto-generated constructor stub
		super() ; 
		this.id = id ; 
		this.nome = nome ; 
		this.descricao = descricao ; 
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString () {
		return String.format("E o produto é : %d , %s, %s", this.id, this.nome, this.descricao);
	}

}
