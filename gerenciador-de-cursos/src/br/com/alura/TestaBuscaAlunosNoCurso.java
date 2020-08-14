package br.com.alura;

public class TestaBuscaAlunosNoCurso {
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
		
		System.out.println("=============================================");
		System.out.println("QUem é o alunho 5617 : ");
		Aluno aluno = javaColecoes.buscaMatriculado ( 5617 ) ; 
		System.out.println("Aluno: " + aluno ) ;
		

		
	}

}
