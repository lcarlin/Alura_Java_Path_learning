package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {
	@Test
	void bonusDeveriaSerZeroParaAltosValores2() {
		BonusService service = new BonusService();
		assertThrows(IllegalArgumentException.class, 
				() -> service.calcularBonus(new Funcionario("Jão", LocalDate.now(), new BigDecimal("11800")))) ; 
	}
	
	@Test
	void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
		BonusService service = new BonusService();
		//assertThrows(IllegalArgumentException.class, 
			//	() -> service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("25000"))));
	
		try {
			service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("25000")));
			fail("nao deu exception");
		} catch (IllegalArgumentException e) {
			assertEquals("Faixa salarial não elegivel à bonuses", e.getMessage());
		}		
	}
	
	// esse caso de teste não faz sentido mais,pois agora a classe calculaBonus gera uma e
	@Test
	void bonusDeveriaSerZeroParaAltosValores() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Jão", LocalDate.now(), new BigDecimal("1800")));		
		assertEquals(new BigDecimal("0.00"), bonus);		
	}
	
	@Test
	void bonusDeveriaSer10Porcento() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Peido", LocalDate.now(), new BigDecimal("1000")));		
		assertEquals(new BigDecimal("100.00"), bonus);		
	}

	@Test
	void bonusDeveriaSer10PorcentoAte1000k (){
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Peido", LocalDate.now(), new BigDecimal("999")));		
		assertEquals(new BigDecimal("99.90"), bonus);		
	}


}
