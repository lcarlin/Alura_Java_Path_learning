package br.com.bytebank.test ;

import br.com.bytebank.banco.modelo.ContaCorrente;
import br.com.bytebank.banco.modelo.ContaPoupanca;

public class TesteContas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// full qualified name FQN
		ContaCorrente cc = new ContaCorrente(111,111 ) ;
		cc.deposita(100 );
		
		ContaPoupanca cp = new ContaPoupanca(222, 222) ; 
		cp.deposita(500);
		
		System.out.println("======================");
		System.out.println("Antes");
		System.out.println(cc.getSaldo());
		System.out.println(cp.getSaldo());
		
		//cc.transfere(10.0, cp) ;
		System.out.println("======================");
		System.out.println("Depois");
		System.out.println(cc.getSaldo());
		System.out.println(cp.getSaldo());

	}

}
