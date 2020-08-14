package br.com.bytebank.test.io;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import br.com.bytebank.banco.modelo.Cliente;
import br.com.bytebank.banco.modelo.ContaCorrente;

public class TesteSerializacao {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente() ;
		cliente.setNome("A Grande Besta 666");
		cliente.setProfissao("Magista");
		cliente.setCpf("212.785.028-90");

		ContaCorrente cc = new ContaCorrente(418,777);
		cc.setTitular(cliente);
		cc.deposita(222.43);
		
		ObjectOutputStream oos = new  ObjectOutputStream (new FileOutputStream("cc.bin")) ;
		oos.writeObject(cc);		
		oos.close();
		
		
		

	}

}
