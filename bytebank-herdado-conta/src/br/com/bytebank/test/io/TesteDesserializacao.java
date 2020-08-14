package br.com.bytebank.test.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import br.com.bytebank.banco.modelo.ContaCorrente;

public class TesteDesserializacao {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = new ObjectInputStream (new FileInputStream ("cc.bin"));
		
		ContaCorrente cc = (ContaCorrente) ois.readObject() ; 
		
		System.out.println(cc.getSaldo());
		System.out.println(cc.getTitular());

	}

}
