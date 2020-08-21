package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class TestandoEstados {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Conta conta = new Conta (); 
		conta.setTitular("Seu Aumiro ");
		conta.setAgencia(2345);
		conta.setNumero(3456);
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(conta);
        
        em.remove(conta);
        
        em.getTransaction().commit();
        
        
	}
}
