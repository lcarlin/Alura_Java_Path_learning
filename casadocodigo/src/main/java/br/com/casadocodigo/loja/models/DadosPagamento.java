package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class DadosPagamento {
	
	public BigDecimal value ;
	
	public DadosPagamento(BigDecimal value ) {
		this.value = value ; 		
	}
	
	public DadosPagamento () {
		
	}
	
	public BigDecimal getValue () {
		return value ;
	}
	

}
