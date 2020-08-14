package br.com.alura.java.io.teste;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TesteSerializacaoCliente {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
//		String nome = "Piroca assassina v3" ;

//		ObjectOutputStream oos = new  ObjectOutputStream (new FileOutputStream("Objecto.bin")) ;
//		oos.writeObject(nome);		
//		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream (new FileInputStream("Objecto.bin")) ;
		
		String nome = (String) ois.readObject() ; 
		System.out.println(nome); ;
		
		ois.close();
		
	}
}
