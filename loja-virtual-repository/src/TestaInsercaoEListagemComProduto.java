import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.modelo.Produto;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Produto comoda = new Produto ("Cômoda", "comoda veritcal ");
		try (Connection connection = new ConnectionFActory().recuperarConexao() ){
			// new PersistenciaProduto(connection).salvarProduto(comoda);
			ProdutoDAO produtoDao = new ProdutoDAO(connection) ;
			produtoDao.salvar(comoda);
			List<Produto> listaDeProdutos = produtoDao.listar() ; 
			listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
		}
		System.out.println(comoda);
	}
}
