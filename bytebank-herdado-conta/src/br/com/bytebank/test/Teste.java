package br.com.bytebank.test;

import br.com.bytebank.banco.modelo.Conta;
import br.com.bytebank.banco.modelo.ContaCorrente;
import br.com.bytebank.banco.modelo.GuardadorDeContas;

public class Teste {
	public static void main(String[] args) {
		GuardadorDeContas guardador = new GuardadorDeContas();

		Conta cc = new ContaCorrente(567, 789);
		guardador.adiciona(cc) ;
		
		Conta cc2 = new ContaCorrente(345, 789);
		guardador.adiciona(cc2) ;
		
		int tamanho = guardador.getQuantideDeElementos() ; 
		System.out.println(tamanho);
		
		Conta ref = guardador.getReferencia(0) ; 
		
		System.out.println(ref.getNumero());

	}
}
