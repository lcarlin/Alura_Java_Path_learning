package br.com.bytebank.banco.modelo;
public class CalculadorDeImpopsto {
	private double totalImposto ;
	
	public void registra (Tributavel t ) {
		double valor = t.getValorImposto();
		this.totalImposto += valor ; 	
	}
	
	public double getTotalImposto() {
		return totalImposto ; 
	}
	
	
}
