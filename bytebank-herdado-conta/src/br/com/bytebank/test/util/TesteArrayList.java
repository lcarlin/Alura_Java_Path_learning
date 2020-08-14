package br.com.bytebank.test.util;

import java.util.ArrayList;

import br.com.bytebank.banco.modelo.Conta;
import br.com.bytebank.banco.modelo.ContaCorrente;

public class TesteArrayList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Generics
		ArrayList<Conta> lista = new ArrayList<Conta>() ;  
		
		//////////////////////
		Conta cc = new ContaCorrente(567, 789);
		lista.add(cc) ;
		
		Conta cc2 = new ContaCorrente(345, 789);
		lista.add(cc2) ;
		
		
		System.out.println(lista.size());
		Conta ref =  lista.get(0);
		System.out.println(ref.getNumero());
		
		lista.remove(0) ; 
		System.out.println("Tamanho: " + lista.size());
		
		Conta cc3 = new ContaCorrente(111, 222);
		lista.add(cc3) ;
		
		Conta cc4 = new ContaCorrente(333, 444);
		lista.add(cc4) ;
		
		for (int i = 0 ; i < lista.size(); i++ ) {
			Object oRef = lista.get(i) ;
			System.out.println(oRef);
		}
		System.out.println("=========================");
		for(Conta  contas : lista) {
			System.out.println(contas);
		}

	}

}
