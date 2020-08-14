package br.com.alura.java.io.teste;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class TesteEscrita {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		OutputStream fos = new FileOutputStream("lorem2.txt") ; 
		Writer osw = new OutputStreamWriter(fos) ; 
		BufferedWriter bw = new BufferedWriter(osw) ;
		
//		String linha = br.readLine() ;
//		while (linha != null  ) {
//			System.out.println(linha);
//			linha = br.readLine() ;
//		}
		
		bw.write("Teste de escrita de arquivo ");
		bw.newLine(); 
		bw.newLine();
		bw.write("Final do arquivo ");
		
		
		bw.close();
	}

}
