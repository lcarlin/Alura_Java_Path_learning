package br.com.alura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestaCurso2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Curso javaColecoes = new Curso("Dominando as coleções de O Java"," Sou Eu "); 
		
		// List<Aula> aulas = javaColecoes.getAulas() ; 
		// System.out.println(aulas);
		// javaColecoes.getAulas().add(new Aula("TRabalhando com Arrays"))

		javaColecoes.adiciona(new Aula ("Trabalhando com Array Lusts", 21)) ;
		javaColecoes.adiciona(new Aula ("Criando uma aula", 20)) ;
		javaColecoes.adiciona(new Aula ("Modelando com coleç~es", 23)) ;
		
		List<Aula> aulasImutaveis = javaColecoes.getAulas() ;	
		System.out.println(aulasImutaveis );

		System.out.println("==========================================");
		List<Aula> aulas = new ArrayList<>(aulasImutaveis) ; 
		Collections.sort (aulas);
		System.out.println(aulas );
		
		System.out.println("==========================================");
		System.out.println(aulas );
		System.out.println(javaColecoes.getTempoTotal() );
		
		System.out.println("==========================================");
		System.out.println(javaColecoes);
		
		// System.out.println(javaColecoes.getAulas());
		
		// aulas.add(new Aula("Trabalhando com ArryLIst", 21)) ;
		// System.out.println(aulas);
		
		// System.out.println(aulas == javaColecoes.getAulas() );
		
		

	}

}
