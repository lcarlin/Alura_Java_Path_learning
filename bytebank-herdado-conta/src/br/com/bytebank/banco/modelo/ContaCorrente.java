package br.com.bytebank.banco.modelo;

public class ContaCorrente extends Conta implements Tributavel {
	
	public ContaCorrente ( int agencia, int numero  ) {
		super(agencia, numero ) ;
		
	}

	@Override
	public void saca(double valor) throws SaldoInsulficienteExcetion {
		// TODO Auto-generated method stub
		double valorASacar = valor + 0.2 ; 
		super.saca(valorASacar);
	}

	@Override
	public void deposita(double valor) {
		// TODO Auto-generated method stub
		super.saldo += valor  ; 	
		
	}

	@Override
	public double getValorImposto() {
		// TODO Auto-generated method stub
		return super.saldo * 0.01 ; 
	}
	
	@Override
	public String toString() {
		return "ContaCorrente, "+ super.toString() ; 
	}
	
}