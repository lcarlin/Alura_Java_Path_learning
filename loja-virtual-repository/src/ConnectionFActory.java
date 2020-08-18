import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFActory {
	public DataSource dataSource ; 
	
	public ConnectionFActory () {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("P!r0c4#93");
		comboPooledDataSource.setMaxPoolSize(15);
		this.dataSource = comboPooledDataSource;
	}
	public Connection recuperarConexao () throws SQLException {
		System.out.println("Criando conexaao com o DB... ");
		return this.dataSource.getConnection() ;
//		return  DriverManager
//				.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC","root","P!r0c4#93");

	}

}
