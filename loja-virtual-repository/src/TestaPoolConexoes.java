import java.sql.SQLException;

public class TestaPoolConexoes {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionFActory connectionFactory = new ConnectionFActory();
		
		for (int i = 0; i < 25; i++) {
			System.out.println("Conexão de numero -> " + i );
			connectionFactory.recuperarConexao() ; 
			
			
		}
		

	}

}
