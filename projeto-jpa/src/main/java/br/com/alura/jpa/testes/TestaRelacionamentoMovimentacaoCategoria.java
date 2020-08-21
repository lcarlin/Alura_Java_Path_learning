package br.com.alura.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacao;

public class TestaRelacionamentoMovimentacaoCategoria {
	public static void main(String[] args) {
		
		Categoria categoria = new Categoria("Viagem") ;
		Categoria categoria2 = new Categoria("Negócios") ;
		
		Conta conta = new Conta () ;
		conta.setId(2L);
		
		
		Movimentacao movimenracao = new Movimentacao() ; 
		movimenracao.setDescricao("Viagem A SP ");
		movimenracao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimenracao.setData(LocalDateTime.now());
		movimenracao.setValor(new BigDecimal(1500.01));
		movimenracao.setCategorias(Arrays.asList(categoria, categoria2)) ; 
		movimenracao.setConta(conta);
		

		Movimentacao movimenracao2 = new Movimentacao() ; 
		movimenracao2.setDescricao("Viagem A DF ");
		movimenracao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimenracao2.setData(LocalDateTime.now());
		movimenracao2.setValor(new BigDecimal(1403.02));
		movimenracao2.setCategorias(Arrays.asList(categoria, categoria2)) ;
		movimenracao2.setConta(conta);

		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
   //     emf.close();
        
		em.getTransaction().begin();
		
		em.persist(categoria);
		em.persist(categoria2);
		em.persist(movimenracao);
		em.persist(movimenracao2);
		
		em.getTransaction().commit();
		em.close();
		
		
		
	}

}
