package br.com.alura;
public class Aluno {
	private String nome ; 
	private int numeroMatricula;
	public Aluno(String nome, int numeroMatricula) {
		// super();
		if ( nome == null ) {
			throw new NullPointerException("Nome não pode ser null");
		}
		
		this.nome = nome;
		this.numeroMatricula = numeroMatricula;
	}
	public String getNome() {
		return nome;
	}
	public int getNumeroMatricula() {
		return numeroMatricula;
	} 
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Aluno :-> " + this.nome + ", MAtricula :-> " + this.numeroMatricula +" ]" ; 
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		//return super.hashCode();
		return this.nome.hashCode() ; 
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Aluno outro = (Aluno ) obj ;
		return this.nome.equals(outro.nome);
		//return super.equals(obj);
	}
	
}
