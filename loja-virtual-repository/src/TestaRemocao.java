import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionFActory factory = new ConnectionFActory();
		Connection connection = factory.recuperarConexao() ; 
		
		PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?",  Statement.RETURN_GENERATED_KEYS);
		stm.setInt(1,3);
		stm.execute();
		
		Integer linhasModificadas = stm.getUpdateCount();
		System.out.println("Linhas deletadas -> "+ linhasModificadas );

	}

}
