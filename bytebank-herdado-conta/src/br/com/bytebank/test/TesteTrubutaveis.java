package br.com.bytebank.test ;

import br.com.bytebank.banco.modelo.CalculadorDeImpopsto;
import br.com.bytebank.banco.modelo.ContaCorrente;
import br.com.bytebank.banco.modelo.SeguroDeVida;

public class TesteTrubutaveis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContaCorrente cc = new ContaCorrente(222,  222234) ;
		cc.deposita(100);
		SeguroDeVida seguro = new SeguroDeVida() ; 
		
		CalculadorDeImpopsto calc = new CalculadorDeImpopsto() ; 
		calc.registra(cc);
		calc.registra(seguro);
		
		System.out.println(calc.getTotalImposto());
		

	}

}
