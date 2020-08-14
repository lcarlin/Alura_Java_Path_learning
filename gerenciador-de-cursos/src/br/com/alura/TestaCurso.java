package br.com.alura;

import java.util.List;

public class TestaCurso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Curso javaColecoes = new Curso("Dominando as coleções de O Java"," Sou Eu "); 
		
		// List<Aula> aulas = javaColecoes.getAulas() ; 
		// System.out.println(aulas);
		// javaColecoes.getAulas().add(new Aula("TRabalhando com Arrays"))

		javaColecoes.adiciona(new Aula ("Trabalhando com Array Lusts", 21)) ;
		javaColecoes.adiciona(new Aula ("Criando uma aula", 20)) ;
		javaColecoes.adiciona(new Aula ("Modelando com coleç~es", 23)) ;
		
		System.out.println(javaColecoes.getAulas());
		
		// aulas.add(new Aula("Trabalhando com ArryLIst", 21)) ;
		// System.out.println(aulas);
		
		// System.out.println(aulas == javaColecoes.getAulas() );

	}

}
