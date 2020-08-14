package br.com.alura.java.io.teste;

import java.io.IOException;
import java.io.PrintStream;

public class TesteEscritaPrintWriter {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		PrintStream ps = new PrintStream("loren4.txt");
		ps.println("Aplpa a minha napa adiposa"); 
		ps.println() ;
		ps.println("e agora com a linha vazia") ;
		
		ps.close();

	}

}
