package br.com.alura;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class Curso {
	private String nome;
	private String instrutor;
	private List<Aula> aulas = new LinkedList<Aula>();
	private Set<Aluno> alunos = new HashSet<>() ; 
	private Map<Integer, Aluno> matriculaParaAluno = new LinkedHashMap<>() ;

	public Curso(String nome, String instrutor) {
		this.nome = nome;
		this.instrutor = instrutor;
	}

	public String getNome() {
		return nome;
	}

	public String getInstrutor() {
		return instrutor;
	}

	public List<Aula> getAulas() {
		// return aulas;
		return Collections.unmodifiableList(aulas);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setInstrutor(String instrutor) {
		this.instrutor = instrutor;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public void adiciona(Aula aula) {
		this.aulas.add(aula);
	}

//	public int getTempoTotal() {
//		int tempoTotal = 0 ; 
//		for ( Aula aula : aulas)  {
//			tempoTotal += aula.getTempo() ;
//		}
//		return tempoTotal ; 
//	}

	public int getTempoTotal() {
		return this.aulas.stream().mapToInt(Aula::getTempo).sum();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Curso:-> " + nome + ", Tempo Total :-> " + this.getTempoTotal() + " ;Aulas:-> " + this.aulas + " ]";
	}

	public void matricula(Aluno aluno) {
		// TODO Auto-generated method stub
		this.alunos.add(aluno) ; 
		this.matriculaParaAluno.put(aluno.getNumeroMatricula() , aluno ) ; 
		
	}
	
	public Set<Aluno> getAlunos() {
		return Collections.unmodifiableSet(alunos) ;
	}

	public boolean estaMatriculado(Aluno aluno) {
		// TODO Auto-generated method stub
		return this.alunos.contains(aluno);
	}

//	public Aluno buscaMatriculado(int numero) {
//		// TODO Auto-generated method stub
//		for (Aluno aluno : alunos) {
//			if (aluno.getNumeroMatricula() == numero ) 
//				return aluno ; 			
//		}
//		throw new NoSuchElementException("MAtricula Nao encontrada  ->" +numero  ) ;
//	}

//	public Aluno buscaMatriculado(int numero) {
//		// TODO Auto-generated method stub
//		return matriculaParaAluno.get(numero) ; 
//	}

	public Aluno buscaMatriculado(int numero) {
		// TODO Auto-generated method stub
		if (!matriculaParaAluno.containsKey(numero) )
			throw new NoSuchElementException() ; 
			
		return matriculaParaAluno.get(numero) ; 
	}
	
}
