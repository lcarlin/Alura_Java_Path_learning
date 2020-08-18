import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionFActory connectionFactory = new ConnectionFActory();
		Connection connection = connectionFactory.recuperarConexao() ; 
		
		PreparedStatement stm = connection.prepareStatement("select ID, NOME, DESCRICAO FROM PRODUTO");
		boolean resultado = stm.execute();
		System.out.println("resulado -> " + resultado);
		
		ResultSet  rst = stm.getResultSet();
		
		while (rst.next() ) {
			Integer id = rst.getInt("ID") ; 
			String nome = rst.getString("NOME");
			String descricao = rst.getNString("DESCRICAO") ; 
			
			System.out.println(id);
			System.out.println(nome);
			System.out.println(descricao);
		}
		
		connection.close();
		System.out.println("conexao encerradas.");

	}

}
