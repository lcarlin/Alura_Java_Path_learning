package br.com.alura.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacao;

public class TestaRelacionamento {

	public static void main(String[] args) {
		
		Conta conta = new Conta()  ; 
		conta.setAgencia(123123);
		conta.setNumero(3434343);
		conta.setSaldo(350.0);
		conta.setTitular("Leo-Nerd");
		
		
		// TODO Auto-generated method stub
		Movimentacao movimentacao  = new Movimentacao () ;
		movimentacao.setData(LocalDateTime.now() );
		movimentacao.setDescricao("Xuraixcaria");
		movimentacao.setValor(new BigDecimal( 250.01));
		movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
		movimentacao.setConta(conta );
		
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(conta);
        em.persist(movimentacao);
        em.getTransaction().commit();
        em.close();
	}
}
