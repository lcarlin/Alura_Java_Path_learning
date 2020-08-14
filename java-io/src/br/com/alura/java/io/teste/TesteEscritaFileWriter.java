package br.com.alura.java.io.teste;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TesteEscritaFileWriter {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
//		OutputStream fos = new FileOutputStream("lorem2.txt") ; 
//		Writer osw = new OutputStreamWriter(fos) ; 
//		BufferedWriter bw = new BufferedWriter(osw) ;
		//FileWriter fw = new FileWriter("lorem3.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("lorem3.txt")) ; 
		
		
//		String linha = br.readLine() ;
//		while (linha != null  ) {
//			System.out.println(linha);
//			linha = br.readLine() ;
//		}
		
		bw.write("Teste de escrita de arquivo ");
		bw.newLine();
		bw.write("uma nova linhas");
		bw.newLine();
		bw.write("uma nova linhas");
		bw.newLine();
		//bw.write(System.lineSeparator());
		//bw.write(System.lineSeparator());
		//fw.write("\r\n");
		bw.write("Final do arquivo ");
		bw.newLine();
//		bw.write(System.lineSeparator());
		bw.close();
	}

}
