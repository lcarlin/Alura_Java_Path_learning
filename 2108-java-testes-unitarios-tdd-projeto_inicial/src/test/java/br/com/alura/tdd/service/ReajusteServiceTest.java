package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

public class ReajusteServiceTest {

	private Funcionario funcionario;
	private ReajusteService service;
	
	@BeforeEach
	public void iniciar() {
		System.out.println("Inicializar " );
		this.service = new ReajusteService();
		this.funcionario =  new Funcionario("OtroJão", LocalDate.now(), new BigDecimal("1000.00"));
	}
	
	@AfterEach
	public void finaliz() {
		System.out.println("E esse é o FIM !");
	}
	
	@BeforeAll
	public static void antesTudo() {
		System.out.println("Inicio de tudo ");
	}
	
	@AfterAll
	public static void depoisTudo() {
		System.out.println("E acabou");
	}

	@Test
	public void reajusteDeveriaSerDe03PortcentoQuandoDesempenhoFRaco() {		
		service.concederReajuste(funcionario, Desempenho.A_DESEJAR);		
		assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
	}

	@Test
	public void reajusteDeveriaSerDe15PortcentoQuandoDesempenhoBOM() {
		service.concederReajuste(funcionario, Desempenho.BOM);		
		assertEquals(new BigDecimal("1150.00"), funcionario.getSalario());
	}

	@Test
	public void reajusteDeveriaSerDe20PortcentoQuandoDesempenhoOtimo() {		
		service.concederReajuste(funcionario, Desempenho.OTIMO);		
		assertEquals(new BigDecimal("1200.00"), funcionario.getSalario());
	}
}
