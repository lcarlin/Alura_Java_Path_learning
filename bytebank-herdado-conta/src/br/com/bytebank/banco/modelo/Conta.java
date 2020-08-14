package br.com.bytebank.banco.modelo;

import java.io.Serializable;

/**
 * 
 * @author LuizAntonio
 *
 */

public abstract class Conta extends Object implements Comparable<Conta>, Serializable {
	
	protected double saldo ;
	private int agencia; 
	private int numero ;
	private transient Cliente titular ;
	private static int total  = 0 ;
	/**
	 * construtor para inicializar o objecto conta a partir da agencias 
	 * @param agencia
	 * @param numero
	 */
	
	public Conta ()  {
	}
	
	public Conta (int agencia, int numero ) {
		Conta.total ++ ; 
		// System.out.println("O total de contas é " + Conta.total );
		this.numero = numero ;
		this.agencia = agencia ;
		
		// System.out.println ("Estou a criar uma nova conta " + this.numero) ; 
		
	}
	//=========================================
	public abstract void deposita (double valor) ;
	/**
	 * valor precisa ser >= Saldo 
	 * 
	 * @param valor
	 * @throws SaldoInsulficienteExcetion
	 */
//	public void deposita (double valor ) {
//		this.saldo += valor  ; 	
//	}
	
	//=========================================
	public void saca ( double valor ) throws SaldoInsulficienteExcetion {
		if ( this.saldo < valor ) {
			// problema
			throw  new SaldoInsulficienteExcetion("There is not enought monwy " + this.saldo + ", Valor : " + valor ) ; 
		}
		
		this.saldo -= valor ;
	}
	
	//================================
	public void transfere ( double valor, Conta destino ) throws SaldoInsulficienteExcetion {
		this.saca (valor) ;
		destino.deposita(valor);
	}
	
	//================================
	public double getSaldo() {
		return this.saldo ; 
	}
	
	//================================
	public int getNumero() {
		return this.numero ; 
	}
	
	//================================
	public void setNumero (int novoNumero ){
		if (novoNumero <= 0 ) {
			System.out.println("Não pode valor menor igual zeroi ");
			return ;
		}		
		this.numero = novoNumero ; 
	}
	
	//================================
	public void setAgencia (int novaAgencia ) {
		if (novaAgencia <= 0 ) {
			System.out.println("Não pode valor menor igual zeroi ");
			return ;
		}
		this.agencia = novaAgencia ; 
	}
	
	//================================
	public int getAgencia () {
		return this.agencia ;
	}
	
	//================================
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	
	//================================
	public Cliente getTitular() {
		return titular;
	}
	
	//================================
	public static int getTotal () {
		return Conta.total ; 
	}
	
	//================================
    @Override
	public String toString() {
		return "Numero: " + this.numero + ", Agencia: " + this.agencia + ",Saldo "+this.saldo;
	}
	//================================
	//================================

    @Override
	public boolean equals(Object ref) {
    	Conta outra = (Conta) ref ; 
		if (this.agencia != outra.agencia ) {
			return  false ; 
		}
		if (this.numero != outra.numero) {
			return false ;
		}
		return true ; 
	}
    
    @Override
    public int compareTo(Conta outra) {
    	// TODO Auto-generated method stub
    	return  Double.compare(this.saldo ,outra.saldo ) ; 
    	// return 0;
    }
	
}