package br.com.bytebank.test.util;

import java.util.ArrayList;

import br.com.bytebank.banco.modelo.Conta;
import br.com.bytebank.banco.modelo.ContaCorrente;

public class TesteArrayListEquals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Generics
		ArrayList<Conta> lista = new ArrayList<Conta>() ;  
		
		//////////////////////
		Conta cc = new ContaCorrente(22, 2);
		lista.add(cc) ;
		
		Conta cc2 = new ContaCorrente(22, 22);
		lista.add(cc2) ;
//		System.out.println(cc.ehIgual(cc2));
		
		Conta cc3 = new ContaCorrente(22, 22);
		//lista.add(cc3) ;
		
		boolean existe = lista.contains(cc3) ;
		System.out.println("Ja existe ? " + existe );
		
		System.out.println("=========================");

		for(Conta  contas : lista) {
			if (contas.equals(cc3)) {
				System.out.println("Ja existe essa conta ");
			}
		}

		for(Conta  contas : lista) {
			System.out.println(contas);
		}

	}

}
