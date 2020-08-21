package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriaContaComSaldo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
   //     emf.close();
   
		Conta conta = new Conta () ;
		conta.setTitular("Taozão");
		conta.setNumero(00001);
		conta.setAgencia(0001);
		conta.setSaldo(5.0);
		
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		em.close();

		EntityManager em2 = emf.createEntityManager();
		System.out.println("Id da conta criada :-> " + conta.getId());
		
		em2.getTransaction().begin();
		
		conta.setSaldo(20.0);
		
		em2.merge(conta ); 
		em2.getTransaction().commit();

	}

}
