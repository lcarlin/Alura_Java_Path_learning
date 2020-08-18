import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFActory factory = new ConnectionFActory();
		try (Connection connection = factory.recuperarConexao() ){
			connection.setAutoCommit(false);
			try (
				PreparedStatement stm = connection.prepareStatement("insert into produto (nome, descricao ) values (?,?);", Statement.RETURN_GENERATED_KEYS);
					){
				adicionarVariavel("SmartTV",  "polegadas", stm);
				adicionarVariavel("Rádio", "radim de píia ", stm);
				
				connection.commit();
				stm.close();
				connection.close();			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("rollback executado");
				connection.rollback();
			}
		}
	}	
	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1,nome);
		stm.setNString(2, descricao);
		if (nome.equals("Rádio")) {
			throw new RuntimeException("Não é possivel adicionar o produto " + nome );
		}
		stm.execute( );
		try ( ResultSet rst = stm.getGeneratedKeys() ) { 
		while (rst.next() ) {
			Integer id = rst.getInt(1) ; 
           System.out.println("o ID criado foi o "  + id );	
		}
		}
	
	}

}
