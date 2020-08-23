package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.dao.MovimentacaoDao;
import br.com.alura.jpa.modelo.Movimentacao;

public class TestaMovimentacoesFiltradasPorDataCriteria {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(em) ;
		
		List<Movimentacao> movimentacaoesFiltradasPorData  = movimentacaoDao.getMovimentacoesFiltradaPorData(null ,null , 2020);
		for (Movimentacao movimentacao : movimentacaoesFiltradasPorData) {
			System.out.println("Descricao :-> " + movimentacao.getDescricao() ) ;
			System.out.println("Valor     :-> " + movimentacao.getValor());
			System.out.println("====================================================");
			
		}
	}

}
