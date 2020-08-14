package br.com.alura;

import java.util.HashSet;
import java.util.Set;

public class TestaAlunos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> alunos = new HashSet<> () ; 
		alunos.add("Rodrigo") ; 
		alunos.add("Alberto") ;
		alunos.add("Nikkoo") ; 
		alunos.add("Sergio") ;
		alunos.add("Renan") ; 
		alunos.add("Alberto") ;
		alunos.add("Mauricio");
		alunos.add("Alberto") ;
		
		System.out.println("==========================================");
		boolean pauloEstaMatriculado = alunos.contains("PAulo"); 
		System.out.println(pauloEstaMatriculado);
			
		System.out.println("==========================================");
		System.out.println(alunos.size());
		
		System.out.println("==========================================");
		System.out.println(alunos);
		
		System.out.println("==========================================");
		for (String aluno : alunos) {
			System.out.println(aluno ) ; 			
		}
		
		System.out.println("==========================================");
		alunos.forEach(aluno -> {
			System.out.println(aluno);
			
		}
		);

	}

}
