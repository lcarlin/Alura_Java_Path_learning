import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionFActory connectionFactory = new ConnectionFActory();
		Connection connection = connectionFactory.recuperarConexao() ; 
		System.out.println("Conexao realizado com o suscesso");
		connection.close();
		System.out.println("conexao encerradas.");
	}

}
