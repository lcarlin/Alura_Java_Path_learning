package br.com.alura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestaListaDeAula {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Aula a1 = new Aula ("Revisitando as Arrays Lists", 21 );
		Aula a2 = new Aula ("Listas de objetos", 20) ; 
		Aula a3 = new Aula ("Relacionamento de listas  objectos", 16 );
		
		ArrayList<Aula> aulas = new ArrayList<>();
		aulas.add(a1) ;
		aulas.add(a2) ; 
		aulas.add(a3) ; 
		
		System.out.println(aulas);
		
		Collections.sort(aulas) ; 
		
		System.out.println(aulas);
		
		Collections.sort(aulas, Comparator.comparing(Aula::getTempo));
		System.out.println(aulas);
		
		aulas.sort(Comparator.comparing(Aula::getTempo));
		System.out.println(aulas);

	}
}