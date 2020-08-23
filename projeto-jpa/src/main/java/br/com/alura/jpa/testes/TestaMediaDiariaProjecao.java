package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.dao.MovimentacaoDao;
import br.com.alura.jpa.modelo.MediaComData;

public class TestaMediaDiariaProjecao {
	public static void main(String[] args) {
		
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
//		MovimentacaoDao dao = new MovimentacaoDao(em) ;
		List<MediaComData> mediaDasMovimentacoes  = new MovimentacaoDao(em).getMediaDiariaDasMovimentacoes () ;
		for (MediaComData resultado : mediaDasMovimentacoes) {
			System.out.println(
					"A média das movimentacoes do dia " + resultado.getDia() + "/" + resultado.getMes() + " é : " + resultado.getValor());
		}
	}
}