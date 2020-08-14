package br.com.bytebank.test ;

import br.com.bytebank.banco.modelo.Conta;
import br.com.bytebank.banco.modelo.ContaCorrente;
import br.com.bytebank.banco.modelo.SaldoInsulficienteExcetion;

public class TesteSaca {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conta conta = new ContaCorrente(123, 321) ; 
		conta.deposita(200);
		
		try {
			conta.saca(201);
		} catch (SaldoInsulficienteExcetion ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println(conta.getSaldo());

	}

}
