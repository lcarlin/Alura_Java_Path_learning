package br.com.bytebank.test.util;

import java.util.ArrayList;
import java.util.List;

public class TestePutrosWrappers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer idadeRef = Integer.valueOf(29) ; // Auto-Boxing
		System.out.println(idadeRef.intValue()); // unboxing
		
		//Double dRef = 3.2 ;
		Double dRef = Double.valueOf(3.2); // autoboxing
		System.out.println(dRef.doubleValue()); ; // unboxig 
		
		Boolean bRef = Boolean.TRUE ;
		System.out.println(bRef.booleanValue());
		
		Number refNumero = Float.valueOf(29.9f) ;
		
		List<Number> lista = new ArrayList <>() ; 
		lista.add(10);
		lista.add(32.6) ; 
		lista.add(25.6f);

	}

}
