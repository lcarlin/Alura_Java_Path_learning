package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class AlteraSaldoConta1 {
	public static void main(String[] args) {
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
        Conta conta01 = em.find(Conta.class, 1L);
        System.out.println("COnta do Sujeito:-> " + conta01.getTitular()); 
        
        em.getTransaction().begin();
        conta01.setSaldo(20.0);
        em.getTransaction().commit();
	}

}
