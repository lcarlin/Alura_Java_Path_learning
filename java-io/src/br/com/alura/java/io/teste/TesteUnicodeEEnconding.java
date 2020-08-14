package br.com.alura.java.io.teste;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TesteUnicodeEEnconding {
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String s = "C" ; 
		System.out.println(s.codePointAt(0));
		
		Charset charset = Charset.defaultCharset() ; 
		System.out.println(charset.displayName());
		
		byte[] bytes = s.getBytes("windows-1252") ;
		System.out.println(bytes.length + ", windows-1252" );
		
		String sNovo = new String(bytes) ;
		System.out.println(sNovo);
		

		// byte[] bytes = s.getBytes("UTF-16") ;
		bytes = s.getBytes("UTF-16") ;
		System.out.println(bytes.length + ", UTF-16" );

		bytes = s.getBytes("UTF-8") ;
		System.out.println(bytes.length + ", UTF-8" );
		
		bytes = s.getBytes(StandardCharsets.US_ASCII) ;
		System.out.println(bytes.length + ", US_ASCII" );
		
	}
}
