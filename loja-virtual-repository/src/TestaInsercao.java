import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionFActory factory = new ConnectionFActory();
		Connection connection = factory.recuperarConexao() ; 
		
		Statement stm = connection.createStatement();
		stm.execute("insert into produto (nome, descricao ) values ('MOUSE', 'mouse sem os fios');",  Statement.RETURN_GENERATED_KEYS);
		ResultSet rst = stm.getGeneratedKeys() ; 
		while (rst.next() ) {
			Integer id = rst.getInt(1) ; 
           System.out.println("o ID criado foi o "  + id );	
		}
	}

}
