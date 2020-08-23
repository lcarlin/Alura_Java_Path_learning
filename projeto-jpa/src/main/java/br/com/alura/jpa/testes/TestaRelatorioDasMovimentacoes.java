package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Conta;

public class TestaRelatorioDasMovimentacoes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();   	
    	//Movimentacao movimentacao = em.find(Movimentacao.class, 3L) ;
        String jpql = "select c from Conta c left join fetch c.movimentacoes";
        TypedQuery<Conta> query = em.createQuery(jpql, Conta.class) ; 
        List<Conta> contas = query.getResultList();
        for (Conta conta : contas ) {
        	System.out.println("Titular   :-> " + conta.getTitular() );
        	System.out.println("Agencia   :-> " + conta.getAgencia() );
        	System.out.println("Numero    :-> " + conta.getNumero());
        	System.out.println("Movimentacooes :-> " + conta.getMovimentacoes() );
        	
        }

	}

}
