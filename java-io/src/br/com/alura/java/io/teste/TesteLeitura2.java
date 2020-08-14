package br.com.alura.java.io.teste;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class TesteLeitura2 {
	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner (new File ("contas.csv"), "UTF-8");
		
		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine(); 
			System.out.println("########################");
			System.out.println(linha);
//			System.out.println("=======================");
//			String [] valores = linha.split(",");
//			System.out.println(Arrays.toString(valores));
//			System.out.println(valores[3]);
			Scanner linhaScanner = new Scanner (linha);
			linhaScanner.useLocale(Locale.US); 
			linhaScanner.useDelimiter(",") ;
			
			
			String tipoConta = linhaScanner.next() ;
			int    agencia   = linhaScanner.nextInt() ;
			int    numero    = linhaScanner.nextInt() ;
			String titular   = linhaScanner.next() ;
			double saldo     = linhaScanner.nextDouble() ;
						
			
			String valorFormatado = String.format("%s - %04d-%08d %20s: %08.2f", tipoConta, agencia, numero, titular, saldo );
			System.out.println(valorFormatado);
			
			// System.out.println(tipoConta + " - " + agencia + " - " + numero + " - " + titular + " - " + saldo + " - "  );
			
			linhaScanner.close();
			
		}
		
		// e aqui finaliza toda a bagaças;. 
		scanner.close();
	}
}
