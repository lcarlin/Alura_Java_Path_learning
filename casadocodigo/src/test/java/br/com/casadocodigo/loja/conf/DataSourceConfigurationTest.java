package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {
	
	@Bean
	@Profile("test")
	public  DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("MyNewPass");
		dataSource.setUrl("jdbc:mysql://localhost:3305/CasaDOCodigo_TST");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		
		return dataSource ;
		
	}

}
