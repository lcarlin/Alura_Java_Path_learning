package br.com.alura;

public class TestaCursosComAlunos {
	public static void main(String[] args) {
Curso javaColecoes = new Curso("Dominando as coleções de O Java"," Sou Eu "); 
		
		// List<Aula> aulas = javaColecoes.getAulas() ; 
		// System.out.println(aulas);
		// javaColecoes.getAulas().add(new Aula("TRabalhando com Arrays"))

		javaColecoes.adiciona(new Aula ("Trabalhando com Array Lusts", 21)) ;
		javaColecoes.adiciona(new Aula ("Criando uma aula", 20)) ;
		javaColecoes.adiciona(new Aula ("Modelando com coleç~es", 23)) ;
		
		Aluno a1 = new Aluno ("Rpdrigo Turini", 34672) ; 
		Aluno a2 = new Aluno ("Guilherme", 5617);
		Aluno a3 = new Aluno ("Mauricio", 17945) ; 
		
		javaColecoes.matricula(a1) ;
		javaColecoes.matricula(a2) ; 
		javaColecoes.matricula(a3) ; 
		
		System.out.println("Todos os alunos matriculados:-> ");
		javaColecoes.getAlunos().forEach(a -> {
			System.out.println(a);
		});
		
		System.out.println("==========================================");
		System.out.println("O Aluno " + a1 + " Esta a ser matriculado ? ");
		System.out.println(javaColecoes.estaMatriculado(a1));
		
		System.out.println("==========================================");
		Aluno turini = new Aluno ("Rpdrigo Turini", 34672);
		
		System.out.println("==========================================");
		System.out.println("E esse caboclo ?");
		//System.out.println(javaColecoes.estaMatriculado(turini));
		System.out.println(a1.equals(turini));
		
	}

}
