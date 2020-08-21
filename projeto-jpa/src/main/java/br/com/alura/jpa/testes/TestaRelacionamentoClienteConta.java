package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Cliente;
import br.com.alura.jpa.modelo.Conta;

public class TestaRelacionamentoClienteConta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conta conta = new Conta () ; 
		conta.setId(1L);
		
		Cliente cliente = new Cliente () ;
		cliente.setNome("Leo-Nerd FAT");
		cliente.setEndereco("Rua ouvidor");
		cliente.setProfissao("EU SOU DEV !!!!");
		cliente.setConta(conta);
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
   //     emf.close();
        
		em.getTransaction().begin();
		em.persist(cliente);

		
		em.getTransaction().commit();
		em.close();
	}

}
