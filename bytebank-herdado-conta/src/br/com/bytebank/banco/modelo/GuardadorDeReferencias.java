package br.com.bytebank.banco.modelo;

public class GuardadorDeReferencias {
	private Object  [] referencias ;
	private int posicaoLivre ;
	
	public GuardadorDeReferencias () {
		this.referencias = new Object[10] ;
		this.posicaoLivre = 0 ; 
	}
	
	public void adiciona(Object ref) {
		// TODO Auto-generated method stub
		this.referencias[this.posicaoLivre] = ref ;
		this.posicaoLivre ++;
	}

	public int getQuantideDeElementos() {
		// TODO Auto-generated method stub
		return this.posicaoLivre ; 
	}

	public Object getReferencia(int pos ) {
		// TODO Auto-generated method stub
		return this.referencias[pos] ;
	}
	
	
	
	
	
}
